import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Character{
    public static Scanner sc =new Scanner(System.in);
    private static int selection;
    static int Level;
    static int user_input;
    static String [] var={"Str","Dex","Con","Int","Wis","Cha"};//creating an array for variables
    static int [] val= new int[6];//creating an array to save the input values
    static String[] bon=new String[6];//another array for calculate and store the the bonus
    static String[] Class = {"Barbarian","Bard","Cleric","Druid","Fighter","Monk","Paladin","Ranger","Rogue",
            "Sorcerer","Warlock","Wizard"};
    static String[] bonus = new String[6];//an array to store the the bonus
    static int decision =0;
    private static int BAB =0;
    static int[] input;
    private static int i;
    public String name;
    public String des;
    private static String characterName=null;
    private static String[] hitdice ={"","1d12","1d8","1d8","1d8","1d10","1d8","1d10","1d10","1d8","1d6","1d8","1d6"};
    public int endGame;


    //prompt level and validating the level
    public static void setLevel(){

        System.out.println("Enter Level : ");//prompt for level
        Level = sc.nextInt();//user input will store a scanner object sc

        int decision=0;//initializing the variable

        //do while loop for invalid level input
        do {
            if (Level < 1) {
                System.out.println("Invalid input\nEnter Level");
                Level=sc.nextInt();
            } else
                break;
        }while(Level <1);

    }

    //display Character classes
    public static void printClass(){

        System.out.println("************************************");
        for(int i=0;i<Class.length;i++) {
            System.out.println((i+1)+". "+Class[i]);

        }
    }

    //prompt the user to select a class and validate it
    public static void promptClass(){
        System.out.println("Select a character from the above classes then, enter the suitable number :");
        selection = sc.nextInt();

        do {
            if (!(selection > 0 && selection < 13)) {
                System.out.println("Inavalid class input!!!\nPlease re-enter :");

                selection = sc.nextInt();
            }

        }while(!(selection > 0 && selection < 13));


    }

    //giving 4 options to the user in which method the should be continued here after
    // and validating the user input
    public static void meth_selection(){
        System.out.println("************************************");
        System.out.println("1. Entering the attributes\n2. Roll 4 dice method\n3. 4 dice method + above 16-1d6" +
                "\n4. Roll Method IX");
        System.out.println("************************************");

        System.out.println("Please select a method from the above options then, enter the suitable number :");
        user_input = sc.nextInt();

        do {
            if (!(user_input > 0 && user_input < 5)) {
                System.out.println("Inavalid input!!!\nPlease re-enter :");

                user_input = sc.nextInt();
            }

        }while(!(user_input > 0 && user_input < 5));

        if(Character.user_input == 1){
            Character.meth_1();
        }else if(Character.user_input == 2){
            Character.meth_2();
        }else if(Character.user_input == 3){
            Character.meth_3();
        }else if(Character.user_input == 4){
            Character.meth_4();
        }

    }

    //a method which is similar to the CW01 1st submission
    public static void meth_1(){


        for(int i=0;i<var.length;i++){

            System.out.println("Enter "+var[i]+" value?" );// prompt the input from the user
            val[i] =sc.nextInt();//store the value in the scanner object

            //validating the input
            if(val[i]<0){
                System.out.println("Invalid Input");
                System.exit(0);
            }else
                continue;
        }



    }
    //creating a method to return the total of 3 dices except the minimum dice
    public static int setmeth_2() {

        int[] dice = new int[4];
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int) (Math.random() * 1000 % 6 + 1);

        }

        //assigning the minimum dice
        int min = dice[0];
        for (int i = 1; i < dice.length; i++) {
            if (dice[i] <= min) {
                min = dice[i];

            }
        }
        int total =(dice[0]+dice[1]+dice[2]+dice[3])-min;
        return total;
    }

    //a method which is same as CW01 3rd submission
    public static void meth_2(){
        do{
            for (int i = 0; i <val.length ; i++) {
                val[i] = setmeth_2();
            }
            bonus();
            prompt_valdesc();
        }while(decision !=1);

    }

    //to calculate the dice value when the level is 16 or above
    public static int setmeth_3() {
        int total =0;
        int x = setmeth_2();
        if(x >=16){
            total=x+(int) (Math.random() * 1000 % 6 + 1);

        }else
            total=x;
        return total;

    }

    //a method which's dicemethod is different from meth_2
    //dice dice value should be different from 16 and above
    public static void meth_3(){

        for (int i = 0; i <val.length ; i++) {
            val[i] = setmeth_3();
        }
        bonus();
        prompt_valdesc();
    }

    //roll 9d6 for the most important attribute and 8d6 for the second most important dice likewise the dice values
    // should be calculated then the least important dice get 4d6
    // and keeps the maximum 3 values of all of the dicerolls
    public static int meth_diceIX(int x){
        int[] diceIX =new int[x];
        int total=0;

        for (int i = 0; i <diceIX.length ; i++) {
            diceIX[i] =(int) (Math.random() * 1000 % 6 + 1);
        }

        for (int i = 0; i <diceIX.length ; i++) {
            int id=i;
            for (int k = 0; k < diceIX.length; k++){
                if(diceIX[k] >diceIX[id])
                    id=k;

                int max =diceIX[id];
                diceIX[id] =diceIX[i];
                diceIX[i]=max;
            }
        }
        total=diceIX[0]+diceIX[1]+diceIX[2];
        return total;
    }

    //to assign the dicetypes to the attributes from the most to least
    public static void meth_4(){
        if(selection ==1){
            val[0]=meth_diceIX(9); val[1]=meth_diceIX(8);val[2]=meth_diceIX(7); val[3]=meth_diceIX(6);
            val[4]=meth_diceIX(5); val[5]=meth_diceIX(4);
        }else if(selection==2){
            val[2]=meth_diceIX(9); val[5]=meth_diceIX(8);val[3]=meth_diceIX(7); val[0]=meth_diceIX(6);
            val[1]=meth_diceIX(5); val[4]=meth_diceIX(4);
        }else if(selection==3){
            val[4]=meth_diceIX(9); val[3]=meth_diceIX(8);val[5]=meth_diceIX(7); val[2]=meth_diceIX(6);
            val[1]=meth_diceIX(5); val[0]=meth_diceIX(4);
        }else if(selection==4){
            val[2]=meth_diceIX(9); val[3]=meth_diceIX(8);val[5]=meth_diceIX(7); val[1]=meth_diceIX(6);
            val[0]=meth_diceIX(5); val[4]=meth_diceIX(4);
        }else if(selection==5){
            val[0]=meth_diceIX(9); val[4]=meth_diceIX(8);val[5]=meth_diceIX(7); val[2]=meth_diceIX(6);
            val[3]=meth_diceIX(5); val[1]=meth_diceIX(4);
        }else if(selection==6){
            val[1]=meth_diceIX(9); val[4]=meth_diceIX(8);val[0]=meth_diceIX(7); val[4]=meth_diceIX(6);
            val[2]=meth_diceIX(5); val[3]=meth_diceIX(4);
        }else if(selection==7){
            val[0]=meth_diceIX(9); val[4]=meth_diceIX(8);val[2]=meth_diceIX(7); val[1]=meth_diceIX(6);
            val[3]=meth_diceIX(5); val[5]=meth_diceIX(4);
        }else if(selection==8){
            val[5]=meth_diceIX(9); val[4]=meth_diceIX(8);val[3]=meth_diceIX(7); val[2]=meth_diceIX(6);
            val[1]=meth_diceIX(5); val[0]=meth_diceIX(4);
        }else if(selection==9){
            val[4]=meth_diceIX(9); val[5]=meth_diceIX(8);val[1]=meth_diceIX(7); val[0]=meth_diceIX(6);
            val[2]=meth_diceIX(5); val[3]=meth_diceIX(4);
        }else if(selection==10){
            val[2]=meth_diceIX(9); val[4]=meth_diceIX(8);val[5]=meth_diceIX(7); val[3]=meth_diceIX(6);
            val[2]=meth_diceIX(5); val[1]=meth_diceIX(4);
        }else if(selection==11){
            val[5]=meth_diceIX(9); val[1]=meth_diceIX(8);val[3]=meth_diceIX(7); val[2]=meth_diceIX(6);
            val[0]=meth_diceIX(5); val[4]=meth_diceIX(4);
        }else
            val[3]=meth_diceIX(9); val[5]=meth_diceIX(8);val[0]=meth_diceIX(7); val[3]=meth_diceIX(6);
            val[2]=meth_diceIX(5); val[4]=meth_diceIX(4);

        bonus();
        prompt_valdesc();
    }

    //calculating the bonus
    public static void bonus(){

        for (int i = 0; i <val.length ; i++) {
            if (val[i] > 11) {
                bonus[i] ="+"+ (val[i] - 10) / 2;

            } else if (val[i] == 10) {
                bonus[i] ="0";

            } else
                bonus[i] =""+((val[i] / 2) - 5);
        }


    }

    //assigning the hitdice by using the user input(selection)
    public static int setHitdice(){
        int x;
        if(selection == 1 ){
            x = 12;
        }else if(selection == 2 ||selection ==3 ||selection ==4
                ||selection ==6 ||selection ==9 ||selection ==11){
            x = 8;
        }else if (selection ==5 ||selection ==7 ||selection ==8){
            x = 10;
        }else
            x = 6;
        return x;
    }

    //calculating hitpoints
    public static int setHitpoints() {

        int sum=0;
        for (int i = 0; i <=Level ; i++) {

            sum +=(int) (Math.random() * 1000 % setHitdice() + 1);
        }
        return (sum/Level)+Integer.parseInt(bonus[2]);

    }

    //assigning the skillpoints with the combine of character class and Int bonus
    public static int setSkillPoints(){
        int skillPoints=0;
        if(Level ==1){
            if(selection ==1 ||selection ==4 || selection ==6) {
                skillPoints = (4 + Integer.parseInt(bonus[3])) * 4;
            }else if(selection ==2 ||selection ==8){
                skillPoints = (6 + Integer.parseInt(bonus[3])) * 4;
            }else if(selection ==9){
                skillPoints = (8 + Integer.parseInt(bonus[3])) * 4;
            }else
                skillPoints = (2 + Integer.parseInt(bonus[3])) * 4;

        }else{
            if(selection ==1 ||selection ==4 || selection ==6) {
                skillPoints = (4 + Integer.parseInt(bonus[3]));
            }else if(selection ==2 ||selection ==8){
                skillPoints = (6 + Integer.parseInt(bonus[3]));
            }else if(selection ==9){
                skillPoints = (8 + Integer.parseInt(bonus[3]));
            }else
                skillPoints = (2 + Integer.parseInt(bonus[3]));
        }
        return skillPoints;
    }

    //print the set of random valued attributes and get prompt from the user to assign the given values
    public static void prompt_valdesc(){

        do {
            for (int i = 0; i < var.length; i++) {
                System.out.println(var[i] + "     : " + "[" + val[i] + "]" + "[" + bonus[i] + "]");
            }
            System.out.println("If you agree with these values,press 1 if not press any other number ");
            decision = sc.nextInt();

        }while(decision !=1);
    }

    //assign BAB by using the equation given in the question
    public static int meth_BAB(){

        if(selection ==1 || selection ==5 || selection ==6 ||selection ==7){
            BAB =Level;
        }else if(selection ==8){
            BAB =(Level*3)/4;
        }else
            BAB =Level/2;

        return BAB;
    }

    //ranking for skills which is selected by user again and again
    public static int ranking(){
        int rank =0;
        for (int j = 0; j <Skill.input.length ; j++) {
            if(Skill.input[i] == Skill.input[i]+1){
                if(rank <Level+3){
                rank+=1;
                } else
                    System.err.println("Maximum number of a selected skill is reached");
            }
        }
        return rank;
    }

    //display the skills and their small descriptions
    public static void print_skill(){
        //prints the skills nd their description
        for(int i = 0;i<Skill.list.size(); i++) {
            Character both = Skill.list.get(i);
            System.out.println((i+1)+". "+both.name+both.des);
        }


    }

    //assign cobat and damage
    // using a for loop to return the values
    public static int cmb_dmg(int x){
        int combat = meth_BAB()+Integer.parseInt(bonus[0]);
        int damage = Integer.parseInt(bonus[0]);

        if(x ==1){
            return combat;
        }else if(x ==2){
            return damage;
        }
        return x;
    }

    //prompt for a name and saving it in variable named characterName
    public static void prompt_name(){
        System.out.println("Please enter a Character name :");
        characterName=sc.next();
    }

    //saving the programme results in a .txt extension file named Player's stats
    public  void write_playerstats(){
        PrintWriter printWriter = null;
        try (FileWriter fileWriter = new FileWriter("Player's stats.txt")) {
            printWriter = new PrintWriter(fileWriter);

        printWriter.println("Hi "+characterName+",");
        printWriter.println("Level   : " + "[" + Level + "]");

        for(int i=0;i<var.length;i++) {
            printWriter.println(var[i] + "     : " + "[" +val[i] + "]" + "[" + bonus[i] + "]");
        }

        printWriter.printf("HP      : "+ "[" + setHitpoints() + "]");
        printWriter.println("\n*****************************************");
        printWriter.println("\nCharacter class that you selected is :\n"+Class[selection-1]);
        printWriter.println("Hitdice :"+" ["+hitdice[selection]+"]");
        printWriter.println("*****************************************");
        printWriter.println("BAB     : " + "["+meth_BAB()+"]");
        printWriter.println("Combat  : "+"["+cmb_dmg(1)+"]");
        printWriter.println("Damage  : "+"["+cmb_dmg(2)+"]");
        printWriter.println("*****************************************");
        printWriter.println("The number of Skill points are :\n"+setSkillPoints());
        printWriter.println("The skill points that you selected are :");

        for (int i = 0; i <Skill.input.length ; i++) {

            Character both = Skill.list.get(Skill.input[i]);
            printWriter.println(both.name+"["+stat_affinity(Skill.input[i])+"]");
        }
        printWriter.println("Rank    : " + "[" + ranking() + "]");
        System.out.println("*****************************************");


        } catch (IOException e) {
        e.printStackTrace();
    }


    }

    //switch case to claculate stat affinity
    private  int stat_affinity(int x) {
        int bon =0;

        for (int i = 0; i < Skill.input.length ; i++) {
            switch (x){
                case 1: case 16: case 17:
                    bon = Integer.parseInt(Character.bonus[1]);
                    break;
                case 4:
                    bon = Integer.parseInt(Character.bonus[0]);
                    break;
                case 3: case 6: case 9: case 11: case 15:
                    bon = Integer.parseInt(Character.bonus[3]);
                    break;
                case 5: case 8: case 13: case 14:
                    bon = Integer.parseInt(Character.bonus[5]);
                    break;
                case 2: case 7: case 10: case 12: case 18:
                    bon = Integer.parseInt(Character.bonus[4]);
                    break;

            }
        }
        return bon;
    }

    //print the results with user inputs and calculations
    public void display(){
        System.out.println("Hi "+characterName+",");
        System.out.println("Level   : " + "[" + Level + "]");

        //a for loop to display the amount of attributes and their bonuses
        for(int i=0;i<var.length;i++) {
            System.out.println(var[i] + "     : " + "[" +val[i] + "]" + "[" + bonus[i] + "]");
        }

        System.out.printf("HP      : "+ "[" + setHitpoints() + "]");//display the hitpoints
        System.out.println("\n*****************************************");
        System.out.println("Character class that you selected is :\n"+Class[selection-1]);
        System.out.println("Hitdice :"+" ["+hitdice[selection]+"]");
        System.out.println("*****************************************");
        System.out.println("BAB     : "+"["+meth_BAB()+"]");
        System.out.println("Combat  : "+"["+cmb_dmg(1)+"]");
        System.out.println("Damage  : "+"["+cmb_dmg(2)+"]");
        System.out.println("*****************************************");
        System.out.println("The number of Skill points are :\n"+setSkillPoints());
        System.out.println("The skill points that you selected are :");
        for (int i = 0; i <Skill.input.length ; i++) {
           System.out.println(Skill.list.get(Skill.input[i]).name+"["+stat_affinity(Skill.input[i])+"]");
           }
        System.out.println("Rank    : " + "[" + ranking() + "]");
        System.out.println("*****************************************");


    }

    //prompt the user to save the results or not doing anything
    public  void savetxt(){

        System.out.println("If you want to save these stats press No.1, if not press anyother number to continue :");
        endGame = sc.nextInt();
        if(endGame==1){
            write_playerstats();
        }

    }

}
