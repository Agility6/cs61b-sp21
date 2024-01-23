package capers;

import java.io.File;

import static capers.Dog.DOG_FOLDER;
import static capers.Utils.*;

/** A repository for Capers 
 * @author Agility6
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(CWD, ".capers");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        // 创建文件
        CAPERS_FOLDER.mkdir();
        DOG_FOLDER.mkdir();
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        /**
         * 1. 在capers目录下创建story文件
         * 2. 检查是否已经存在,如果不存在将text直接赋值到newText，否则进行拼接
         * 3. 写入
         *
         * 注意：
         * 1. writeContents会判断是否存在，自动创建文件
         * 2. 因为是字符串类型所以不需要序列化
         */
        File storyFile = join(".capers", "story");
        String newText;

        if (!storyFile.exists()) {
            newText = text;
        } else {
            // 读取当前story下的内容
            String oldText = readContentsAsString(storyFile);
            newText = oldText + "\n" + text;
        }

        writeContents(storyFile, newText);
        System.out.println(newText);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        /**
         * 1. 创建dog
         * 2. 保存
         */
        Dog newDog = new Dog(name, breed, age);
        newDog.saveDog();

        System.out.println(newDog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {

        /**
         * 1. 获取当前狗对象
         * 2. 打印birthday
         * 3. 更新存储
         */

        Dog currentDog = Dog.fromFile(name);
        currentDog.haveBirthday();
        currentDog.saveDog();
    }
}
