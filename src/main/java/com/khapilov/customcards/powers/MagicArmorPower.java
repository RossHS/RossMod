package com.khapilov.customcards.powers;

import com.khapilov.customcards.actions.MagicArmorAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @author Ross Khapilov
 * @version 1.0 17.10.2018
 */
public class MagicArmorPower extends AbstractPower {
    public static final String POWER_ID = "Magic Armor";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magic Armor");
    public static final String NAME = powerStrings.NAME;

    public MagicArmorPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        description = powerStrings.DESCRIPTIONS[0];
        img = ImageMaster.loadImage("powers/magicArmor.png");
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (int i = 0; i < amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new MagicArmorAction());
        }
    }
}
