public class PlayerTest {
    public static void main(String[] args)
    {

        System.out.println("Starting Player test");

        Player myPlayer = new Player("Bagguette", 1500);
        System.out.println("Balance: " + myPlayer.getBalance());
        System.out.println("Name: " + myPlayer.getName());
        System.out.println("Withdraw 100$, then deposit 800$");
        myPlayer.withdraw(100);
        myPlayer.deposit(800);
        System.out.println("New Balance: " + myPlayer.getBalance() + "\nNow it will move to tile 15(Directly), then 45 normally");
        myPlayer.changePositionDirect(15);
        System.out.println("Position: " + myPlayer.getPosition());
        myPlayer.moveUp(45);
        System.out.println("Position: " + myPlayer.getPosition());

        System.out.println("Increment doubles by 2, then reset");
        myPlayer.incrNumDoubles();
        myPlayer.incrNumDoubles();
        System.out.println(myPlayer.getNumDoubles());
        myPlayer.getNumDoubles();
        System.out.println(myPlayer.getNumDoubles());

        // TODO PROPERTY




    }
    
}
