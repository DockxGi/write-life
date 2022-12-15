package command.playing;

import character.outfit.Outfit;
import character.player.domain.Player;
import command.ArgumentCommand;
import command.describers.LevelOfDetail;
import command.describers.OutfitDescriber;
import game.GameModel;

import static command.describers.LevelOfDetail.LOW;
import static command.describers.LevelOfDetail.MEDIUM;

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
            return;
        }
        if (!hasArgument(splitted)){
            showOutfit(player, LOW);
            return;
        }
        if (splitted[1].equals("detailed")){
            showOutfit(player, MEDIUM);
        }
    }

    private void showOutfit(Player player, LevelOfDetail levelOfDetail) {
        Outfit outfit = player.getOutfit();
        if (outfit == null){
            System.out.println("You are not wearing anything.");
            return;
        }
        OutfitDescriber outfitDescriber = new OutfitDescriber();
        String outfitDescription = outfitDescriber.describe(outfit, levelOfDetail);
        System.out.print(outfitDescription);
    }
}
