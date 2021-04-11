package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StudentRegistrationFormTests extends TestBase {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Other",
            mobile = faker.number().digits(10),
            dayOfBirth = "10",
            monthOfBirth = "May",
            yearOfBirth = "1988",
            subject1 = "Chemistry",
            subject2 = "Commerce",
            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music",
            picture = "1.jpg",
            currentAddress = faker.address().fullAddress(),
            state = "Uttar Pradesh",
            city = "Merrut";

    @Test
    void successfulFillFormTest() {
        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(mobile);
        });

        step("Set date", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").find(byText(monthOfBirth)).click();
            $(".react-datepicker__year-select").selectOptionByValue(yearOfBirth);
            $(".react-datepicker__day--0" + dayOfBirth).click();
        });

        step("Set subject", () -> {
            $("#subjectsContainer").click();
            $("#subjectsInput").setValue(subject1).pressEnter();
        });

        step("Set hobbey", () -> {
            $("#hobbiesWrapper").$(byText(hobby1)).click();
        });

        step("Upload file", () -> {
            $("#uploadPicture").uploadFromClasspath(picture);
        });

        step("Set address", () -> {
            $("#currentAddress").val(currentAddress);
            $("#state").scrollTo().click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });

        step("Submit form", () -> {
            $("#submit").click();
        });

        step("Verify successful form submit", () -> {
            $(".modal-content").shouldHave(text(firstName),
                    text(lastName),
                    text(email),
                    text(gender),
                    text(mobile),
                    text(subject1),
                    text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                    text(hobby1),
                    text(picture),
                    text(currentAddress),
                    text(state + " " + city));
        });
    }

    @Test
    void negativeFillFormTest() {
        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(mobile);
        });

        step("Set date", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").find(byText(monthOfBirth)).click();
            $(".react-datepicker__year-select").selectOptionByValue(yearOfBirth);
            $(".react-datepicker__day--0" + dayOfBirth).click();
        });

        step("Set subject", () -> {
            $("#subjectsContainer").click();
            $("#subjectsInput").setValue(subject1).pressEnter();
        });

        step("Set hobbey", () -> {
            $("#hobbiesWrapper").$(byText(hobby1)).click();
        });

        step("Upload file", () -> {
            $("#uploadPicture").uploadFromClasspath(picture);
        });

        step("Set address", () -> {
            $("#currentAddress").val(currentAddress);
            $("#state").scrollTo().click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });

        step("Submit form", () -> {
            $("#submit").click();
        });

        step("Verify successful form submit", () -> {
            $(".modal-content").shouldHave(text(firstName),
                    text(lastName),
                    text(email),
                    text(gender),
                    text(mobile),
                    text(subject1),
                    text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                    text(hobby1),
                    text(picture),
                    text(currentAddress));
            $(".modal-content").shouldNotHave(text(state + " error " + city));
        });
    }
}
