package app;

import java.io.IOException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManagePhoneServlet extends HttpServlet {

    // Идентификатор для сериализации/десериализации.
    private static final long serialVersionUID = 1L;

    // Основной объект, хранящий данные телефонной книги.
    private Phonebook phonebook;

    public ManagePhoneServlet() {
        // Вызов родительского конструктора.
        super();

        // Создание экземпляра телефонной книги.
        try {
            this.phonebook = Phonebook.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // Валидация номера телефона и генерация сообщения об ошибке в случае невалидных данных.
    private String validateTelephoneNumber(String number) {
        String error_message = "";
        Matcher matcher = Pattern.compile("[0-9+#-]{2,50}").matcher(number);
        if (!matcher.matches()) {
            error_message += "Номер телефона должен быть от 2 до 50 символов: цифра, +, -, #.";
        }

        return error_message;
    }

    // Реакция на GET-запросы.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Обязательно ДО обращения к любому параметру нужно переключиться в UTF-8,
        // иначе русский язык при передаче GET/POST-параметрами превращается в "кракозябры".
        request.setCharacterEncoding("UTF-8");

        // В JSP нам понадобится сама телефонная книга. Можно создать её экземпляр там,
        // но с архитектурной точки зрения логичнее создать его в сервлете и передать в JSP.
        request.setAttribute("phonebook", this.phonebook);

        // Хранилище параметров для передачи в JSP.
        HashMap<String, String> jsp_parameters = new HashMap<String, String>();

        // Диспетчеры для передачи управления на разные JSP (разные представления (view)).
        RequestDispatcher dispatcher_for_phone = request.getRequestDispatcher("/jsp/Phone.jsp");
        RequestDispatcher dispatcher_for_delete = request.getRequestDispatcher("/jsp/ManagePersonEdit.jsp");
        // Действие (action) и идентификатор записи (id) над которой выполняется это действие.
        String action = request.getParameter("action");
        String personId = request.getParameter("personId");
        String numberId = request.getParameter("numberId");

        Person editable_person;
        switch (action) {
            // Добавление записи.

            case "addNumber":
                editable_person = this.phonebook.getPerson(personId);
                // Подготовка параметров для JSP.
                jsp_parameters.put("editable_number", "");

                jsp_parameters.put("title", "Экран добавления телефона");
                jsp_parameters.put("next_action", "add_go");
                jsp_parameters.put("next_action_label", "Добавить номер");

                // Установка параметров JSP.
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_phone.forward(request, response);
                break;

            // Редактирование записи.
            case "editNumber":
                editable_person = this.phonebook.getPerson(personId);
                // Подготовка параметров для JSP.
                jsp_parameters.put("numberId", numberId);
                jsp_parameters.put("editable_number", editable_person.getPhones().get(numberId));
                jsp_parameters.put("title", "Экран редактирования телефона");
                jsp_parameters.put("next_action", "edit_go");
                jsp_parameters.put("next_action_label", "Сохранить номер");

                // Установка параметров JSP.
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_phone.forward(request, response);
                break;

            // Удаление записи.
            case "deleteNumber":

                editable_person = this.phonebook.getPerson(personId);
                // Если запись удалось удалить...
                if (phonebook.deleteTelephoneNumber(editable_person, numberId)) {

                    jsp_parameters.put("current_action_result_label", "Удаление выполнено успешно");

                }
                // Если запись не удалось удалить (например, такой записи нет)...
                else {

                    jsp_parameters.put("current_action_result_label", "Ошибка удаления (возможно, запись не найдена)");
                }

                // Установка параметров JSP.
                jsp_parameters.put("next_action", "edit_go");
                jsp_parameters.put("next_action_label", "Сохранить");
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_delete.forward(request, response);
                break;
        }
    }


    // Реакция на POST-запросы.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Обязательно ДО обращения к любому параметру нужно переключиться в UTF-8,
        // иначе русский язык при передаче GET/POST-параметрами превращается в "кракозябры".
        request.setCharacterEncoding("UTF-8");

        // В JSP нам понадобится сама телефонная книга. Можно создать её экземпляр там,
        // но с архитектурной точки зрения логичнее создать его в сервлете и передать в JSP.
        request.setAttribute("phonebook", this.phonebook);

        // Хранилище параметров для передачи в JSP.
        HashMap<String, String> jsp_parameters = new HashMap<String, String>();

        // Диспетчеры для передачи управления на разные JSP (разные представления (view)).
        RequestDispatcher dispatcher_for_managerEdit = request.getRequestDispatcher("/jsp/ManagePersonEdit.jsp");
        RequestDispatcher dispatcher_for_phone = request.getRequestDispatcher("/jsp/Phone.jsp");

        String add_go = request.getParameter("add_go");
        String edit_go = request.getParameter("edit_go");
        String personId = request.getParameter("personId");
        String numberId = request.getParameter("numberId");
        String phoneNumber = request.getParameter("phoneNumber");

        // Добавление записи.
        if (add_go != null) {

            Person editable_person = this.phonebook.getPerson(personId);

            // Валидация телефона.
            String error_message = this.validateTelephoneNumber(phoneNumber);

            // Если данные верные, можно производить добавление.
            if (error_message.equals("")) {

                // Если запись удалось добавить...
                if (this.phonebook.addTelephoneNumber(editable_person, phoneNumber)) {

                    jsp_parameters.put("current_action_result_label", "Добавление выполнено успешно");
                }
                // Если запись НЕ удалось добавить...
                else {

                    jsp_parameters.put("current_action_result_label", "Ошибка добавления");
                }

                // Установка параметров JSP.

                jsp_parameters.put("next_action", "edit_go");
                jsp_parameters.put("next_action_label", "Сохранить");
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_managerEdit.forward(request, response);
            }
            // Если в данных были ошибки, надо заново показать форму и сообщить об ошибках.
            else {
                // Подготовка параметров для JSP.
                jsp_parameters.put("title", "Экран добавления телефона");
                jsp_parameters.put("editable_number", "");
                jsp_parameters.put("next_action", "add_go");
                jsp_parameters.put("next_action_label", "Добавить номер");
                jsp_parameters.put("error_message", error_message);

                // Установка параметров JSP.
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_phone.forward(request, response);
            }
        }
        // Добавление записи.
        if (edit_go != null) {

            Person editable_person = this.phonebook.getPerson(personId);

            // Валидация телефона.
            String error_message = this.validateTelephoneNumber(phoneNumber);

            // Если данные верные, можно производить добавление.
            if (error_message.equals("")) {

                // Если запись удалось добавить...
                if (this.phonebook.editTelephoneNumber(editable_person, phoneNumber, numberId)) {

                    jsp_parameters.put("current_action_result_label", "Добавление выполнено успешно");
                }
                // Если запись НЕ удалось добавить...
                else {

                    jsp_parameters.put("current_action_result_label", "Ошибка добавления");
                }

                // Установка параметров JSP.

                jsp_parameters.put("next_action", "edit_go");
                jsp_parameters.put("next_action_label", "Сохранить");
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_managerEdit.forward(request, response);
            }
            // Если в данных были ошибки, надо заново показать форму и сообщить об ошибках.
            else {
                // Подготовка параметров для JSP.
                jsp_parameters.put("title", "Экран редактирования телефона");
                jsp_parameters.put("editable_number", "");
                jsp_parameters.put("next_action", "add_go");
                jsp_parameters.put("next_action_label", "Сохранить номер");
                jsp_parameters.put("error_message", error_message);

                // Установка параметров JSP.
                request.setAttribute("person", editable_person);
                request.setAttribute("jsp_parameters", jsp_parameters);

                // Передача запроса в JSP.
                dispatcher_for_phone.forward(request, response);
            }
        }
    }
}
