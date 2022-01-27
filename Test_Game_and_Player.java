class Test_Game_and_Player
{
        public static void main(String[] args)
            {
                // I will use snake case
                Game game = new Game("squares.txt", "cards.txt");
                game.printBoard();
                game.createPlayer("Daniel");
                game.createPlayer("Bobet");

                // System.out.println("Players" + game.getPlayers());
                for(int i = 0; i < game.getPlayers().size(); i++)
                    {
                        System.out.println(game.getPlayers().get(i).getName());
                    }
                game.getPlayers().get(0).changePositionDirect(0);
                //game.buyProperty(0,"Old Kent Road");

                game.printPropertyData(1);
            }
}
