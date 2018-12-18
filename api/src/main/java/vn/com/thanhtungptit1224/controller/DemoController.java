package vn.com.thanhtungptit1224.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.com.thanhtungptit1224.Student;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    @GetMapping(value = "/home")
    public String handleRequest(
            ServerHttpRequest request,
            ServerHttpResponse response,
            Model model) throws Exception {

        Student student = new Student();
        student.setName("Coder Tien Sinh");

        List<String> books = new ArrayList<String>();
        books.add("book1");
        books.add("book2");
        student.setBooks(books);

        model.addAttribute("model", student);
        return "index";
    }

    @PostMapping(value = "/submitStudentInfo")
    public String submitStudentInfo(Model model, @ModelAttribute("model") Student student) {

        List<String> listBooks = student.getBooks();
        boolean bookSameName = checkSameBookName(listBooks);

        String message = "Update success !!!";
        if (bookSameName) {
            message = "Books must have different name !!!";
        }
        model.addAttribute("message", message);

        model.addAttribute("model", student);
        return "index";
    }

    private boolean checkSameBookName(List<String> listBooks) {
        for (int i = 0; i < listBooks.size(); i++) {
            String firstBookName = listBooks.get(i);
            for (int j = i + 1; j < listBooks.size(); j++) {
                String secondBookName = listBooks.get(j);
                if (firstBookName.equalsIgnoreCase(secondBookName)) {
                    return true;
                }
            }
        }
        return false;
    }

}
