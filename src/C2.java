public class C2{

    public static void main(String[] args){

        Character character = new Character();
        //do while loop for repeat the stuffs if user wish to regenerate another set of values
        do {
            Character.setLevel();
            Character.printClass();
            Character.promptClass();
            Character.meth_selection();
            Character.bonus();
            Skill.fileReading();
            Character.print_skill();
            Skill.userinput();
            Character.prompt_name();
            character.display();
            character.savetxt();
            System.out.println("\nPress any number to make another Character, if not press No.1 to quit" +
                    " the programme :");
            character.endGame = Character.sc.nextInt();
        }while (character.endGame != 1);

    }
}