import java.io.*;

/**
 * 用于处理用户数据的存储和加载。
 */
public class Storage {
    private static final String USER_DATA_FILE = "userdata.ser";  // 用户数据文件名

    // 加载指定用户名的用户数据，如果文件不存在或读取失败则创建一个新用户。
    public static User loadUser(String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            User user = (User) ois.readObject();  // 读取用户对象
            if (user.getName().equals(name)) {  // 检查用户名是否匹配
                return user;
            }
        } catch (FileNotFoundException e) {
            System.out.println("未找到保存的数据，开始一个新的用户会话。");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new User(name);  // 如果文件不存在或者无法读取，创建一个新用户
    }

    // 将用户数据保存到文件中。
    public static void saveUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(user);  // 写入用户对象到文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


