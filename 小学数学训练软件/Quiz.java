import java.util.List;

/**
 * 代表一次测验，包含一系列的数学题目。
 */
public class Quiz {
    private List<Question> questions;  // 存储数学题目的列表

    //构造函数，创建一个新的测验实例。
    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    //获取测验中的所有题目。
    public List<Question> getQuestions() {
        return questions;
    }
}


