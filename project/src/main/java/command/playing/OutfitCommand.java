package command.playing;

import character.outfit.Outfit;
import character.player.domain.Player;
import command.ArgumentCommand;
import command.describers.OutfitDescriber;
import game.GameModel;

import static command.describers.LevelOfDetail.LOW;

public class OutfitCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "outfit";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        Player player = game.getPlayer();
        if (player == null){
            System.out.println("You have to enter the world with a player to see your outfit.");
        }
        if (!hasArgument(splitted)){
            showOutfitInLowDetail(player);
            return;
        }
        if (splitted[1].equals("detailed")){
            //todo: show outfit in more detail
        }
    }

    private void showOutfitInLowDetail(Player player) {
        Outfit outfit = player.getOutfit();
        if (outfit == null){
            System.out.println("You are not wearing anything.");
            return;
        }
        OutfitDescriber outfitDescriber = new OutfitDescriber();
        String outfitDescription = outfitDescriber.describe(outfit, LOW);
        System.out.print(outfitDescription);
    }
}
