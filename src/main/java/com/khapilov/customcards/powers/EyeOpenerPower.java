package com.khapilov.customcards.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @author Ross Khapilov
 * @version 1.0 29.10.2018
 */
public class EyeOpenerPower extends AbstractPower {
    public static final String POWER_ID = "Eye-Opener";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public EyeOpenerPower(AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        description = powerStrings.DESCRIPTIONS[0];
        img = ImageMaster.loadImage("powers/eyeOpener.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Eye-Opener"));
    }


    @Override
    public void update(int slot) {
        super.update(slot);
    }
}
