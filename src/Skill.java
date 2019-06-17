import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Skill {

    private String name;
    private String des;
    public static int[] input;


    static LinkedList<Character> list= new LinkedList<Character>();

    private void Skill(String name, String des) {
        this.name=name;
        this.des=des;

    }

    public static void fileReading() {

        try {
            Scanner sc = new Scanner(new File("C:\\Users\\Naweed Imraan\\IdeaProjects\\DnD2\\src\\Skill.txt"));

            while (sc.hasNext()) {

                String name = sc.next();
                String des = sc.nextLine();
                Character abt_skill = new Character();
                abt_skill.name = name;
                abt_skill.des = des;
                list.add(abt_skill);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void userinput() {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        input = new int[Character.setSkillPoints()];
        for (int i = Character.setSkillPoints(); i > 0; i--) {
            System.out.println("Select your skills from the given list above : " + "(" + i + " left)");
            input[count] = sc.nextInt() - 1;
            count++;

        }
    }
}
