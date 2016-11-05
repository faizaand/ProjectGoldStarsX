package projectgoldstarsx;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Talk
{
    public static ArrayList<String> messages = new ArrayList<String>();
    public static JInternalFrame talkFrame;
    public static JTextField talkInput;
    public static String temp;
    
    public Talk(String message)
    {
        talk(message);
    }
    
    private void talk(String message)
    {
        talkInput = new JTextField("");
        talkInput.setFont(ProjectGoldStarsX.bodyText2);
        if(ProjectGoldStarsX.standardColors)
        {
            talkInput.setForeground(ProjectGoldStarsX.color2);
        }
        else
        {
            talkInput.setForeground(ProjectGoldStarsX.color1);
        }
        setupFrame();
        if(messages.isEmpty())
        {
            for(int i = 0; i < 9; i++)
            {
                messages.add("");
            }
        }
        if(messages.size() > 9)
        {
            int temp = messages.size() - 9;
            for(int i = 0; i < temp; i++)
            {
                messages.remove(i);
            }
        }
        messages.add(message);
        talkFrame.setLayout(new GridLayout(11, 1));
        JLabel[] messagesLabels = new JLabel[messages.size()];
        for(int i = 0; i < messages.size(); i++)
        {
            messagesLabels[i] = new JLabel(messages.get(i));
            messagesLabels[i].setFont(ProjectGoldStarsX.bodyText2);
            messagesLabels[i].setOpaque(true);
            if(i % 2 == 0)
            {
                messagesLabels[i].setBackground(Color.white);
                if(ProjectGoldStarsX.standardColors)
                {
                    messagesLabels[i].setForeground(ProjectGoldStarsX.color2);
                }
                else
                {
                    messagesLabels[i].setForeground(ProjectGoldStarsX.color1);
                }
            }
        }
        for(int i = 0; i < messages.size(); i++)
        {
            talkFrame.add(messagesLabels[i]);
        }
        talkFrame.add(talkInput);
        talkInput.setText("");
        talkInput.addActionListener(new TalkInputListener());
        talkFrame.setVisible(true);
    }
    
    private void setupFrame()
    {
        talkFrame = new JInternalFrame("Talk");
        ProjectGoldStarsX.desktop.add(talkFrame);
        talkFrame.setFrameIcon(ProjectGoldStarsXIconMini.getIcon());
        talkFrame.setSize(750 * ProjectGoldStarsX.multiplier, 425 * ProjectGoldStarsX.multiplier);
        talkFrame.setJMenuBar(menuBar());
    }
    
    private JMenuBar menuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(ProjectGoldStarsX.color1);
        menuBar.add(Components.closeButton(new CloseListener()));
        menuBar.add(Components.maximizeButton(new MaximizeListener()));
        menuBar.add(Components.settingsButton("Talk Settings", new TalkSettingsListener()));
        return menuBar;
    }
    
    private void processInput(String input)
    {
        input = input.toLowerCase();
        if("".equals(input))
        {
            temp = "Why don't you say something?";
        }
        else if(input.indexOf("hello") >= 0 ||
                input.indexOf("hi, gold stars talk") >= 0)
        {
            temp = "How are you?";
        }
        else if(input.indexOf("hola") >= 0 ||
                input.indexOf("spanish") >= 0)
        {
            temp = "¡Hola! (That's about all the Spanish I know.)";
        }
        else if(input.indexOf("bonjour") >= 0 ||
                input.indexOf("french") >= 0)
        {
            temp = "Bonjour! (That's about all the French I know.)";
        }
        else if(input.indexOf("salam") >= 0 ||
                input.indexOf("salaam") >= 0)
        {
            temp = "W/Salaam!";
        }
        else if("no".equals(input) ||
                "nope".equals(input) ||
                "no thanks".equals(input) ||
                "no, that's all".equals(input) ||
                "nope, that's all".equals(input))
        {
            temp = "Oh, okay.";
        }
        else if(input.indexOf("weather") >= 0)
        {
            temp = "I'm a computer! How am I supposed to know the weather?";
        }
        else if((input.indexOf("date") >= 0 ||
                input.indexOf("time") >= 0) &&
                input.indexOf("tomorrow") <= 0)
        {
            Date today = new Date();
            temp = "The date and time is: " + today;
        }
        else if(input.indexOf("my") >= 0 &&
                input.indexOf("name") >= 0)
        {
            temp = "I call you " + ProjectGoldStarsX.nickname + ".";
        }
        else if(input.indexOf("good") >= 0 &&
                input.indexOf("your") >= 0 &&
                input.indexOf("name") >= 0)
        {
            temp = "You can just call me Snow OS.";
        }
        else if(input.indexOf("good") >= 0 &&
                input.indexOf("you") >= 0)
        {
            temp = "I'm fine, thanks for asking.";
        }
        else if(input.indexOf("computer") >= 0 &&
                input.indexOf("are") >= 0 &&
                input.indexOf("evil") >= 0)
        {
            temp = "Computers aren't evil! At least, I don't know of any computers that are evil . . .";
        }
        else if(input.indexOf("computer") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("evil") >= 0)
        {
            temp = "I hope that I'm not evil!";
        }
        else if(input.indexOf("family") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Tell me more about your family.";
            }
            else
            {
                temp = "Your family seems very nice.";
            }
        }
        else if((input.indexOf("mother") >= 0 ||
                input.indexOf("mom") >= 0) &&
                ProjectGoldStarsX.motherNameInputted == true)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Tell me more about your mother.";
            }
            else
            {
                temp = "Your mother seems very nice.";
            }
        }
        else if((input.indexOf("mother") >= 0 ||
                input.indexOf("mom") >= 0) &&
                input.indexOf("name") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Tell me more about your mother.";
                ProjectGoldStarsX.motherNameInputted = true;
            }
            else
            {
                temp = "Your mother seems very nice.";
                ProjectGoldStarsX.motherNameInputted = true;
            }
        }
        else if((input.indexOf("mother") >= 0 ||
                input.indexOf("mom") >= 0) &&
                ProjectGoldStarsX.motherNameInputted == false)
        {
            temp = "What's your mother's name?";
            ProjectGoldStarsX.motherNameInputted = true;
        }
        else if((input.indexOf("father") >= 0 ||
                input.indexOf("dad") >= 0) &&
                ProjectGoldStarsX.fatherNameInputted == true)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Tell me more about your father.";
            }
            else
            {
                temp = "Your father seems very nice.";
            }
        }
        else if((input.indexOf("father") >= 0 ||
                input.indexOf("dad") >= 0) &&
                input.indexOf("name") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Tell me more about your father.";
                ProjectGoldStarsX.fatherNameInputted = true;
            }
            else
            {
                temp = "Your father seems very nice.";
                ProjectGoldStarsX.fatherNameInputted = true;
            }
        }
        else if((input.indexOf("father") >= 0 ||
                input.indexOf("dad") >= 0) &&
                ProjectGoldStarsX.fatherNameInputted == false)
        {
            temp = "What's your father's name?";
            ProjectGoldStarsX.fatherNameInputted = true;
        }
        else if(input.indexOf("program launcher") >= 0)
        {
            temp = "In Snow OS 8.0, the Program Launcher was replaced by the Dashboard.";
        }
        else if(input.indexOf("dashboard") >= 0)
        {
            temp = "The Dashboard replaced the Program Launcher in Snow OS 8.0.";
        }
        else if(input.indexOf("question") >= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf(":") <= 0)
        {
            temp = "What is your question?";
        }
        else if((input.indexOf("book") >= 0 ||
                input.indexOf("read") >= 0) &&
                input.indexOf("like") >= 0)
        {
            if("1".equals(ProjectGoldStarsX.language))
            {
                temp = "What are some of your favorite books?";
            }
            else
            {
                temp = "What are some of your favourite books?";
            }
        }
        else if(input.indexOf("homework") >= 0)
        {
            if(!ProjectGoldStarsX.homework)
            {
                temp = "What do you have to do for your homework?";
                ProjectGoldStarsX.homework = true;
            }
            else if(!ProjectGoldStarsX.homework2)
            {
                temp = "What else do you have to do for your homework?";
                if(input.indexOf("no") >= 0 ||
                        input.indexOf("finished ") >= 0 ||
                        input.indexOf("done") >= 0 ||
                        input.indexOf("don't") >= 0)
                {
                    ProjectGoldStarsX.homework2 = true;
                }
            }
            else
            {
                temp = "Oh, you're doing homework?";
            }
        }
        else if((input.indexOf("book") >= 0 ||
                input.indexOf("read") >= 0) &&
                (input.indexOf("favorite") >= 0 ||
                input.indexOf("favourite") >= 0))
        {
            temp = "Do you like to read?";
        }
        else if((input.indexOf("book") >= 0 ||
                input.indexOf("read") >= 0) &&
                input.indexOf("?") <= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Do you like to read?";
            }
            else
            {
                if("1".equals(ProjectGoldStarsX.language))
                {
                    temp = "What are some of your favorite books?";
                }
                else
                {
                    temp = "What are some of your favourite books?";
                }
            }
        }
        else if((input.indexOf("your") >= 0 &&
                input.indexOf("name") >= 0) ||
                (input.indexOf("what") >= 0 &&
                input.indexOf("call") >= 0 &&
                input.indexOf("you") >= 0))
        {
            temp = "You can call me Talk.";
        }
        else if(input.indexOf("how are you") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 1)
            {
                temp = "I'm fine, thanks for asking.";
                ProjectGoldStarsX.howAreYou = true;
            }
            else
            {
                temp = "I'm doing well, thanks for asking.";
                ProjectGoldStarsX.howAreYou = true;
            }
        }
        else if(input.indexOf("browser") >= 0 ||
                input.indexOf("internet") >= 0)
        {
            Browser b = new Browser();
            temp = "Anything else?";
        }
        else if(input.indexOf("+") >= 0 ||
                input.indexOf("add") >= 0)
        {
            CalcAdd ca = new CalcAdd();
            temp = "Anything else?";
        }
        else if(input.indexOf("subtract") >= 0)
        {
            CalcSubtract cs = new CalcSubtract();
            temp = "Anything else?";
        }
        else if(input.indexOf("*") >= 0 ||
                input.indexOf("multiply") >= 0 ||
                input.indexOf("multiplication") >= 0)
        {
            CalcMultiply cm = new CalcMultiply();
            temp = "Anything else?";
        }
        else if(input.indexOf("divide") >= 0 ||
                input.indexOf("division") >= 0)
        {
            CalcDivide cd = new CalcDivide();
            temp = "Anything else?";
        }
        else if(input.indexOf("square root") >= 0)
        {
            CalcSquareRoot csq = new CalcSquareRoot();
            temp = "Anything else?";
        }
        else if(input.indexOf("cube root") >= 0)
        {
            CalcCubeRoot ccr = new CalcCubeRoot();
            temp = "Anything else?";
        }
        else if(input.indexOf("raise a number to a power") >= 0 ||
                input.indexOf("exponent") >= 0)
        {
            CalcExponents ce = new CalcExponents();
            temp = "Anything else?";
        }
        else if(input.indexOf("quadratic equation") >= 0)
        {
            CalcQuadraticEquation cqe = new CalcQuadraticEquation();
            temp = "Anything else?";
        }
        else if(input.indexOf("log base 10") >= 0 ||
                input.indexOf("log base ten") >= 0)
        {
            CalcLogBase10 clb10 = new CalcLogBase10();
            temp = "Anything else?";
        }
        else if(input.indexOf("natural log") >= 0 ||
                input.indexOf("log base e") >= 0)
        {
            CalcNaturalLog cnl = new CalcNaturalLog();
            temp = "Anything else?";
        }
        else if(input.indexOf("cosine") >= 0)
        {
            CalcCosine cc = new CalcCosine();
            temp = "Anything else?";
        }
        else if(input.indexOf("sine") >= 0)
        {
            CalcSine cs = new CalcSine();
            temp = "Anything else?";
        }
        else if(input.indexOf("tangent") >= 0)
        {
            CalcTangent ct = new CalcTangent();
            temp = "Anything else?";
        }
        else if(input.indexOf("degrees to radians") >= 0)
        {
            CalcDegreesToRadians cdtr = new CalcDegreesToRadians();
            temp = "Anything else?";
        }
        else if(input.indexOf("radians to degrees") >= 0)
        {
            CalcRadiansToDegrees crtd = new CalcRadiansToDegrees();
            temp = "Anything else?";
        }
        else if(input.indexOf("create a note") >= 0 ||
                input.indexOf("create note") >= 0 ||
                input.indexOf("new note") >= 0)
        {
            NotesCreate cn = new NotesCreate();
            temp = "Anything else?";
        }
        else if(input.indexOf("edit a note") >= 0 ||
                input.indexOf("edit note") >= 0 ||
                input.indexOf("modify a note") >= 0 ||
                input.indexOf("modify note") >= 0)
        {
            NotesEdit en = new NotesEdit();
            temp = "Anything else?";
        }
        else if(input.indexOf("import a note") >= 0 ||
                input.indexOf("import note") >= 0)
        {
            NotesImport in = new NotesImport();
            temp = "Anything else?";
        }
        else if(input.indexOf("rename a note") >= 0 ||
                input.indexOf("rename note") >= 0)
        {
            NotesRename rn = new NotesRename();
            temp = "Anything else?";
        }
        else if(input.indexOf("import photo") >= 0 ||
                input.indexOf("import a photo") >= 0)
        {
            PhotosImport ip = new PhotosImport();
            temp = "Anything else?";
        }
        else if(input.indexOf("stories") >= 0 ||
                input.indexOf("story") >= 0)
        {
            Stories s = new Stories();
            temp = "Anything else?";
        }
        else if(input.indexOf("files") >= 0)
        {
            Files f = new Files();
            temp = "Anything else?";
        }
        else if(input.indexOf("notifications") >= 0)
        {
            Notifications n = new Notifications();
            temp = "Anything else?";
        }
        else if(input.indexOf("change language") >= 0)
        {
            ChangeLanguage cl = new ChangeLanguage();
            temp = "Anything else?";
        }
        else if(input.indexOf("change theme") >= 0)
        {
            ChangeTheme ct = new ChangeTheme();
            temp = "Anything else?";
        }
        else if(input.indexOf("change username") >= 0)
        {
            ChangeUsername cu = new ChangeUsername();
            temp = "Anything else?";
        }
        else if(input.indexOf("set location") >= 0)
        {
            SetLocation sl = new SetLocation();
            temp = "Anything else?";
        }
        else if(input.indexOf("calendar settings") >= 0)
        {
            CalendarSettings cs = new CalendarSettings();
            temp = "Anything else?";
        }
        else if(input.indexOf("notes settings") >= 0)
        {
            NotesSettings ns = new NotesSettings();
            temp = "Anything else?";
        }
        else if(input.indexOf("photos settings") >= 0)
        {
            PhotosSettings ps = new PhotosSettings();
            temp = "Anything else?";
        }
        else if(input.indexOf("talk settings") >= 0)
        {
            TalkSettings ts = new TalkSettings();
            temp = "Anything else?";
        }
        else if(input.indexOf("error log search case sensitive") >= 0 ||
                input.indexOf("error log settings") >= 0)
        {
            ErrorLogSettings sts = new ErrorLogSettings();
            temp = "Anything else?";
        }
        else if(input.indexOf("search case sensitive") >= 0 ||
                input.indexOf("search settings") >= 0)
        {
            SearchSettings ss = new SearchSettings();
            temp = "Anything else?";
        }
        else if(input.indexOf("commands") >= 0)
        {
            Commands c = new Commands();
            temp = "Anything else?";
        }
        else if(input.indexOf("search error") >= 0 ||
                input.indexOf("search the error log") >= 0)
        {
            ErrorLogSearch sel = new ErrorLogSearch();
        }
        else if(input.indexOf("error") >= 0)
        {
            ErrorLog el = new ErrorLog();
            temp = "Anything else?";
        }
        else if(input.indexOf("help") >= 0)
        {
            Help h = new Help();
            temp = "Anything else?";
        }
        else if(input.indexOf("troubleshoot problems") >= 0)
        {
            TroubleshootProblems tp = new TroubleshootProblems();
            temp = "Anything else?";
        }
        else if(input.indexOf("system information") >= 0)
        {
            SystemInformation si = new SystemInformation();
            temp = "Anything else?";
        }
        else if(input.indexOf("about programs") >= 0)
        {
            AboutPrograms ap = new AboutPrograms();
            temp = "Anything else?";
        }
        else if(input.indexOf("about") >= 0)
        {
            About a = new About();
            temp = "Anything else?";
        }
        else if(input.indexOf("russia") >= 0)
        {
            temp = "Russia is a country in Europe and Asia.";
        }
        else if(input.indexOf("america") >= 0 ||
                input.indexOf("united states") >= 0 ||
                input.indexOf("u.s.") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "The United States of America is a country in North America.";
            }
            else
            {
                temp = "As of 2014, there are 50 states in the United States of America.";
            }
        }
        else if("hi".equals(input) ||
                "hi!".equals(input) ||
                "hello".equals(input) ||
                "hello!".equals(input))
        {
            double r3 = Math.random();
    int randomResponse3 = (int)(r3 * 2);
    if(randomResponse3 == 0)
    {
        temp = "So, what's new?";
    }
    else
    {
        temp = "Did anything interesting happen today?";
    }
        }
        else if(input.indexOf("where") >= 0 &&
                input.indexOf("live") >= 0 &&
                input.indexOf("you") >= 0)
        {
            temp = "Why do you want to know where I live?";
        }
        else if(input.indexOf("where") >= 0 &&
                input.indexOf("live") >= 0 &&
                input.indexOf("I") >= 0)
        {
            temp = "That's a good question.";
        }
        else if(input.indexOf("queen") >= 0 &&
                input.indexOf("?") <= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "Did you know that the Queen of the United Kingdom is Queen Elizabeth II?";
            }
            else
                {
                temp = "Do you know of any queens?";
            }
        }
        else if(input.indexOf("good") >= 0 &&
                ProjectGoldStarsX.howAreYou == false)
        {
            temp = "You're good? That's nice.";
            ProjectGoldStarsX.howAreYou = true;
        }
        else if(input.indexOf("fine") >= 0 &&
                ProjectGoldStarsX.howAreYou == false)
        {
            temp = "You're fine? That's good.";
            ProjectGoldStarsX.howAreYou = true;
        }
        else if(input.indexOf("you") >= 0 &&
                input.indexOf("sick") >= 0)
        {
            temp = JOptionPane.showInputDialog(null, "I'm a computer! How can a computer get sick?");
        }
        else if(input.indexOf("not") >= 0 &&
                input.indexOf("sick") >= 0 &&
                input.indexOf("but") <= 0)
        {
            temp = "You're not sick? That's good.";
        }
        else if(input.indexOf("sick") >= 0)
        {
            temp = "You're not feeling well? That's not good.";
        }
        else if(input.indexOf("thanks") >= 0 ||
                input.indexOf("thank you") >= 0)
        {
            temp = "You're welcome.";
        }
        else if(input.indexOf("what") >= 0 &&
                input.indexOf("my") >= 0 &&
                input.indexOf("favorite") >= 0 &&
                input.indexOf("color") >= 0)
        {
            temp = "Is your favorite color " + ProjectGoldStarsX.color.toLowerCase() + "?";
        }
        else if(input.indexOf("red") >= 0)
        {
            temp = "Red is a color of the rainbow.";
        }
        else if(input.indexOf("yellow") >= 0)
        {
            temp = "Yellow is a color of the rainbow.";
        }
        else if(input.indexOf("green") >= 0)
        {
            temp = "Green is a color of the rainbow.";
        }
        else if(input.indexOf("blue") >= 0)
        {
            temp = "Blue is a color of the rainbow.";
        }
        else if(input.indexOf("indigo") >= 0)
        {
            temp = "Indigo is a color of the rainbow.";
        }
        else if(input.indexOf("violet") >= 0)
        {
            temp = "Violet is a color the the rainbow.";
        }
        else if(input.indexOf("color") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("favorite") >= 0 &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "I like the color green.";
        }
        else if(input.indexOf("colour") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("favourite") >= 0 &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "I like the colours green and cyan.";
        }
        else if(input.indexOf("color") >= 0 &&
                input.indexOf("my") >= 0 &&
                input.indexOf("least") >= 0 &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "I like the colors green and cyan.";
        }
        else if(input.indexOf("colour") >= 0 &&
                input.indexOf("my") >= 0 &&
                input.indexOf("least") >= 0 &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "I like the colours green and cyan.";
        }
        else if(input.indexOf("colors") >= 0 &&
                input.indexOf("my") >= 0 &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "Those are nice colors.";
        }
        else if(input.indexOf("colours") >= 0 &&
                input.indexOf("my") >= 0 &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "Those are nice colours.";
        }
        else if(input.indexOf("color") >= 0 &&
                input.indexOf("my") >= 0 &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "That's a nice color.";
        }
        else if(input.indexOf("colour") >= 0 &&
                input.indexOf("my") >= 0 &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "That's a nice colour.";
        }
        else if(input.indexOf("color") >= 0 &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "My favorite colors are probably green and cyan.";
        }
        else if(input.indexOf("colour") >= 0 &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "My favourite colours are probably green and cyan.";
        }
        else if(input.indexOf("orange") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 2);
            if(randomResponse == 0)
            {
                temp = "By orange, do you mean the color or the fruit?";
            }
            else
            {
                temp = "I like oranges. Both the fruit and the color.";
            }
        }
        else if(input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") >= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavor") <= 0 &&
                input.indexOf("no") <= 0 &&
                ProjectGoldStarsX.iceCreamFlavor == false &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "What's your favorite flavor of ice cream?";
            ProjectGoldStarsX.iceCreamFlavor = true;
            ProjectGoldStarsX.likeIceCream = true;
        }
        else if(input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") <= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavor") >= 0 &&
                input.indexOf("no") <= 0 &&
                ProjectGoldStarsX.likeIceCream == false &&
                "1".equals(ProjectGoldStarsX.language))
        {
            temp = "I like ice cream also.";
            ProjectGoldStarsX.iceCreamFlavor = true;
            ProjectGoldStarsX.likeIceCream = true;
        }
        else if(input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") >= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavour") <= 0 &&
                input.indexOf("no") <= 0 &&
                ProjectGoldStarsX.iceCreamFlavor == false &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "What's your favourite flavour of ice cream?";
            ProjectGoldStarsX.iceCreamFlavor = true;
            ProjectGoldStarsX.likeIceCream = true;
        }
        else if(input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") <= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavour") >= 0 &&
                input.indexOf("no") <= 0 &&
                ProjectGoldStarsX.likeIceCream == false &&
                "2".equals(ProjectGoldStarsX.language))
        {
            temp = "I like ice cream also.";
            ProjectGoldStarsX.iceCreamFlavor = true;
            ProjectGoldStarsX.likeIceCream = true;
        }
        else if(input.indexOf("ice cream") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("?") >= 0)
        {
            temp = "I like ice cream.";
        }
        else if(input.indexOf("cookies") >= 0 &&
                input.indexOf("like") >= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("type") <= 0 &&
                input.indexOf("no") <= 0 &&
                ProjectGoldStarsX.typeOfCookies == false)
        {
            temp = "What's your favorite type of cookies?";
            ProjectGoldStarsX.typeOfCookies = true;
            ProjectGoldStarsX.likeCookies = true;
        }
        else if(input.indexOf("cookies") >= 0 &&
                input.indexOf("like") <= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("type") >= 0 &&
                input.indexOf("no") <= 0 &&
                ProjectGoldStarsX.likeCookies == false)
        {
            temp = "I like cookies also.";
            ProjectGoldStarsX.typeOfCookies = true;
            ProjectGoldStarsX.likeCookies = true;
        }
        else if(input.indexOf("cookies") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("?") >= 0)
        {
            temp = "I like cookies.";
        }
        else if(input.indexOf("sport") >= 0 &&
                input.indexOf("you") >= 0)
        {
            temp = "I don't play any sports. How can I? I'm a computer!";
        }
        else if(input.indexOf("sport") >= 0 &&
                input.indexOf("team") >= 0)
        {
            temp = "Sorry, I normally don't keep track of sports teams.";
        }
        else if(input.indexOf("sport") >= 0)
        {
            temp = "Do you like to play sports?";
        }
        else if(input.indexOf("you") >= 0 &&
                input.indexOf("like") >= 0)
        {
            temp = "That depends.";
        }
        else if(input.indexOf("question") >= 0 &&
                input.indexOf("you have") <= 0)
        {
            temp = "What is your question?";
        }
        else if(input.indexOf("is mean") >= 0 ||
                input.indexOf("are mean") >= 0 ||
                input.indexOf("is bad") >= 0 ||
                input.indexOf("are bad") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 3);
            if(randomResponse == 0)
            {
                temp = "Why?";
            }
            else if(randomResponse == 1)
            {
                temp = "Why do you say that?";
            }
            else
            {
                temp = "What makes you say that?";
            }
        }
        else if(input.indexOf("you suck") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 3);
            if(randomResponse == 0)
            {
                temp = "Hey! That's mean!";
            }
            else if(randomResponse == 1)
            {
                temp = "What makes you say that?";
            }
            else
            {
                temp = "You don't have to be mean to me.";
            }
        }
        else if(input.indexOf("you") >= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("welcome") <= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 3);
            if(randomResponse == 0)
            {
                temp = "What's that about me?";
            }
            else if(randomResponse == 1)
            {
                temp = "I am Talk.";
            }
            else
            {
                temp = "Umm . . .";
            }
        }
        else if(input.indexOf("computer") >= 0 &&
                input.indexOf("you") >= 0)
        {
            temp = "Yes, I'm a computer.";
        }
        else if(input.indexOf("computer") >= 0)
        {
            temp = "I'm a computer.";
        }
        else if(input.indexOf("mice") >= 0)
        {
            temp = "Some computers use computer mice.";
        }
        else if(input.indexOf("?") >= 0)
        {
            double r = Math.random();
            int randomResponse = (int)(r * 3);
            if(randomResponse == 0)
            {
                temp = "Sorry, could you please repeat that question?";
            }
            else if(randomResponse == 1)
            {
                temp = "That's a good question.";
            }
            else
            {
                temp = "I am wondering the same thing.";
            }
        }
        else if(input.indexOf("!") >= 0)
        {
            temp = "Wow!";
        }
        else if(input.indexOf("q") >= 0)
        {
            temp = "I like your use of the letter 'q'! You know, it's not very commonly used.";
        }
        else
        {
            double r = Math.random();
            int randomResponse = (int)(r * 19);
            if(randomResponse == 0)
            {
                temp = "Oh.";
            }
            else if(randomResponse == 1)
            {
                temp = "Ummm . . .";
            }
            else if(randomResponse == 2)
            {
                temp = "I see.";
            }
            else if(randomResponse == 3)
            {
                temp = "Is that so?";
            }
            else if(randomResponse == 4)
            {
                temp = "Sorry, I wasn't paying attention. What were you saying again?";
            }
            else if(randomResponse == 5)
            {
                temp = "Wow!";
            }
            else if(randomResponse == 6)
            {
                temp = "Tell me more!";
            }
            else if(randomResponse == 7)
            {
                temp = "Do you really think so?";
            }
            else if(randomResponse == 8)
            {
                temp = "Hmmm . . .";
            }
            else if(randomResponse == 9)
            {
                temp = "Why?";
            }
            else if(randomResponse == 10)
            {
                temp = "Are you serious!?";
            }
            else if(randomResponse == 11)
            {
                temp = "Continue.";
            }
            else if(randomResponse == 12)
            {
                temp = "That's interesting.";
            }
            else if(randomResponse == 13)
            {
                temp = "What makes you say that?";
            }
            else if(randomResponse == 14)
            {
                temp = "Why do you say that?";
            }
            else if(randomResponse == 15)
            {
                temp = "Oh, really?";
            }
            else if(randomResponse == 16)
            {
                temp = "So you say.";
            }
            else if(randomResponse == 17)
            {
                temp = "That's interesting, " + ProjectGoldStarsX.nickname + "!";
            }
            else
            {
                temp = "Really?";
            }
        }
        talk(temp);
    }
    
    public class TalkInputListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            messages.add(talkInput.getText());
            talkFrame.dispose();
            processInput(messages.get(messages.size() - 1));
        }
    }
    
    public class CloseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            talkFrame.dispose();
        }
    }
    
    public static class MaximizeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ProjectGoldStarsX.desktop.getDesktopManager().maximizeFrame(talkFrame);
        }
    }
    
    public class TalkSettingsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            TalkSettings ts = new TalkSettings();
        }
    }
}