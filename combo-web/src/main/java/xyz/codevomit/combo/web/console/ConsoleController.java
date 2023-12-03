package xyz.codevomit.combo.web.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import xyz.codevomit.combo.data.DataService;
import xyz.codevomit.combo.data.repo.ReceivedMessageRepository;
import xyz.codevomit.combo.data.telegram.ReceivedMessage;

import java.util.List;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    ReceivedMessageRepository msgRepo;

    @GetMapping
    public String main(Model model){

        List<ReceivedMessage> messages = msgRepo.findAll(Sort.by(Sort.Direction.DESC, "serverTime"));

        model.addAttribute("receivedMessages", messages);
        return "console_main";
    }
}
