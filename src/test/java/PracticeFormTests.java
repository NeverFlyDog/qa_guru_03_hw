import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillPracticeForm() {
        open("/automation-practice-form");

        var firstName = "John";
        var lastName = "Doe";
        var email = "john.doe@example.com";
        var gender = "Male";
        var mobile = "1234567890";
        var dateOfBirth = "2 June,1980";
        var subjects = new String[]{"Computer Science", "Maths", "Physics"};
        var hobbies = new String[]{"Sports", "Music"};
        var filename = "test.jpg";
        var address = "793 Arleen Street";
        var state = "Haryana";
        var city = "Panipat";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        // Gender
        $("#genterWrapper").$(byText(gender)).click();

        // Mobile
        $("#userNumber").setValue(mobile);

        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1980");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--002:not(.react-datepicker__day--outside-month)").click();

        // Subjects
        $("#subjectsInput").setValue(subjects[0]).pressEnter();
        $("#subjectsInput").setValue(subjects[1]).pressEnter();
        $("#subjectsInput").setValue(subjects[2]).pressEnter();

        // Hobbies
        $("#hobbiesWrapper").$(byText(hobbies[0])).click();
        $("#hobbiesWrapper").$(byText(hobbies[1])).click();

        // Picture
        $("#uploadPicture").uploadFromClasspath(filename);

        // Current Address
        $("#currentAddress").setValue(address);

        // State
        $("#state").click();
        $("#state").$$("div")
                .filterBy(text(state))
                .first()
                .click();

        // City
        $("#city").click();
        $("#city").$$("div")
                .filterBy(text(city))
                .first()
                .click();

        // Submit
        $("#submit").click();

        // Result
        $(".modal-body").shouldHave(text(firstName + " " + lastName));
        $(".modal-body").shouldHave(text(gender));
        $(".modal-body").shouldHave(text(mobile));
        $(".modal-body").shouldHave(text(dateOfBirth));
        $(".modal-body").shouldHave(text(String.join(", ", subjects)));
        $(".modal-body").shouldHave(text(String.join(", ", hobbies)));
        $(".modal-body").shouldHave(text(filename));
        $(".modal-body").shouldHave(text(address));
        $(".modal-body").shouldHave(text(state + " " + city));
    }
}
