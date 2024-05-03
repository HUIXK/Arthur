import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("unused")
public class GUI extends JFrame {
    private JComboBox<String> gradeBox;
    private JComboBox<String> questionTypeBox;
    private JComboBox<Integer> questionCountBox;
    private JTextField questionField;
    private JTextField answerField;
    private JButton submitButton;
    private JButton nextButton;
    private JButton reviewMistakesButton;
    private Quiz currentQuiz;
    private List<Question> questions;
    private int currentIndex = 0;
    private User currentUser;

// 构造器：设置窗体标题，初始化用户和界面
    public GUI() {
        super("数学小测验");
        this.currentUser = new User("学生1");  // 示例用户
        createGUI();
        loadUserState();
    }

// 根据选择的年级和题型生成题目
    private void generateQuestions() {
        String selectedGrade = (String) gradeBox.getSelectedItem();
        String selectedType = (String) questionTypeBox.getSelectedItem();
        int numQuestions = (int) questionCountBox.getSelectedItem();
        questions = new ArrayList<>();
        for (int i = 0; i < numQuestions; i++) {
            questions.add(Create_Question.generateQuestion(selectedGrade, selectedType));
        }
        currentIndex = 0;
        loadQuestion();
    }

    // 构建GUI界面
    private void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());
        // 顶部面板：用于选择年级、题型和题目数量
        JPanel topPanel = new JPanel();
        gradeBox = new JComboBox<>(new String[]{"一年级", "二年级", "三年级"});
        questionTypeBox = new JComboBox<>(new String[]{"加法", "减法", "乘法", "除法"});
        questionCountBox = new JComboBox<>(new Integer[]{10, 15, 20, 25});
    
        topPanel.add(new JLabel("选择年级:"));
        topPanel.add(gradeBox);
        topPanel.add(new JLabel("选择题型:"));
        topPanel.add(questionTypeBox);
        topPanel.add(new JLabel("题目数量:"));
        topPanel.add(questionCountBox);
    
        // 初始化时更新题型选择框的选项
        updateQuestionTypeOptions();
    
        // 添加事件监听器，以响应用户操作
        gradeBox.addActionListener(e -> updateQuestionTypeOptions());
        questionTypeBox.addActionListener(e -> generateQuestions());
        questionCountBox.addActionListener(e -> generateQuestions());

    
        // 中间面板用于问题和答案
        JPanel middlePanel = new JPanel();
        questionField = new JTextField(20);
        questionField.setEditable(false);
        answerField = new JTextField(5);
        submitButton = new JButton("提交");
        submitButton.addActionListener(new SubmitAction());
        nextButton = new JButton("下一题");
        nextButton.addActionListener(new NextAction());
        nextButton.setEnabled(false);

        reviewMistakesButton = new JButton("复习错题");
        reviewMistakesButton.addActionListener(e -> reviewMistakes());
        topPanel.add(reviewMistakesButton);

        middlePanel.add(questionField);
        middlePanel.add(new JLabel("="));
        middlePanel.add(answerField);
        middlePanel.add(submitButton);
        middlePanel.add(nextButton);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // 加载当前题目到界面
    private void loadQuestion() {
        if (currentIndex < questions.size()) {
            Question currentQuestion = questions.get(currentIndex);
            questionField.setText(currentQuestion.getText());
            answerField.setText("");
            nextButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "测验完成!");
        }
    }

    // 根据年级更改题型选择
    private void updateQuestionTypeOptions() {
        String selectedGrade = (String) gradeBox.getSelectedItem();
        String[] availableTypes;
        if (selectedGrade.equals("三年级")) {
            availableTypes = new String[]{"乘法", "除法"};
        } else {
            availableTypes = new String[]{"加法", "减法"};
        }
        questionTypeBox.setModel(new DefaultComboBoxModel<>(availableTypes));
    }

    // 提交答案
    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int userAnswer = Integer.parseInt(answerField.getText());
                Question currentQuestion = questions.get(currentIndex);
                if (userAnswer != currentQuestion.getAnswer()) {
                    currentUser.recordWrongAnswer(currentQuestion);
                    JOptionPane.showMessageDialog(null, "错误！请再试一次。");
                } else {
                    JOptionPane.showMessageDialog(null, "正确！");
                    nextButton.setEnabled(true);
                    submitButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "请输入有效的数字答案。");
            }
        }
    }

    // 加载下一题
    private class NextAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentIndex++;
            loadQuestion();
            submitButton.setEnabled(true);
        }
    }

    // 复习错题功能
    private void reviewMistakes() {
        List<Question> wrongQuestions = new ArrayList<>(currentUser.getWrongAnswers().keySet());
        if (!wrongQuestions.isEmpty()) {
            questions = wrongQuestions;
            currentIndex = 0;
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "没有错题需要复习！");
        }
    }

    // 加载用户状态
    private void loadUserState() {
        // 假定Storage类处理用户数据加载
        this.currentUser = Storage.loadUser(currentUser.getName());
        if (currentUser == null) {
            currentUser = new User("学生1");  // 默认用户
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}




    
        


