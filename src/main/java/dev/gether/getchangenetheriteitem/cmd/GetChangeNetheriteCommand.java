package dev.gether.getchangenetheriteitem.cmd;

import dev.gether.getchangenetheriteitem.GetChangeNetheriteItem;
import dev.gether.getchangenetheriteitem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class GetChangeNetheriteCommand extends Command {


    private final List<String> netheriteItems = Arrays.asList(
                                                    "NETHERITE_SWORD",
                                                    "NETHERITE_AXE",
                                                    "NETHERITE_HOE",
                                                    "NETHERITE_HELMET",
                                                    "NETHERITE_CHESTPLATE",
                                                    "NETHERITE_LEGGINGS",
                                                    "NETHERITE_BOOTS",
                                                    "NETHERITE_SHOVEL",
                                                    "NETHERITE_PICKAXE");

    private GetChangeNetheriteItem plugin;
    public GetChangeNetheriteCommand(@NotNull String name, GetChangeNetheriteItem plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        if(!player.hasPermission("getchangenetherite.use"))
            return false;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(!netheriteItems.contains(itemStack.getType().name())){
            player.sendMessage(ColorFixer.addColors(plugin.getConfig().getString("lang.netherite-item.cancel.chat")));
            player.sendTitle(
                    ColorFixer.addColors(plugin.getConfig().getString("lang.netherite-item.cancel.title")),
                    ColorFixer.addColors(plugin.getConfig().getString("lang.netherite-item.cancel.subtitle")),
                    10,
                    50,
                    10
                    );
            return false;
        }

        ItemStack diamondItem = new ItemStack(Material.valueOf(itemStack.getType().name().replace("NETHERITE", "DIAMOND")));
        diamondItem.setItemMeta(itemStack.getItemMeta());

        player.getInventory().setItemInMainHand(diamondItem);

        player.sendMessage(ColorFixer.addColors(plugin.getConfig().getString("lang.netherite-item.accept.chat")));
        player.sendTitle(
                ColorFixer.addColors(plugin.getConfig().getString("lang.netherite-item.accept.title")),
                ColorFixer.addColors(plugin.getConfig().getString("lang.netherite-item.accept.subtitle")),
                10,
                50,
                10
        );

        return false;
    }
}
