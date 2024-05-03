import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 表示一个用户，包含用户的基本信息和错题记录。
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;  // 用户名
    private Map<Question, Integer> wrongAnswers;  // 错题记录，题目及错误次数

    // 构造函数，创建一个新的用户实例。
    public User(String name) {
        this.name = name;
        this.wrongAnswers = new HashMap<>();  // 初始化错题记录
    }

    // 记录用户的错误答案。
    public void recordWrongAnswer(Question question) {
        wrongAnswers.put(question, wrongAnswers.getOrDefault(question, 0) + 1);  // 记录错误次数
    }

    // 获取用户的错题记录。
    public Map<Question, Integer> getWrongAnswers() {
        return wrongAnswers;
    }

    // 获取用户的用户名。
    public String getName() {
        return name;
    }
}



