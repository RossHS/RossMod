package com.khapilov.customcards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/**
 * @author Ross khapilov
 * @version 1.0 11.10.2018
 */
public class TankUpPower extends AbstractPower {
    public static final String POWER_ID = "Tank Up!";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Tank Up!");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int strength = -1;

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        strength--;
    }

    public TankUpPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        img = ImageMaster.loadImage("powers/tankUp.png");
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (-strength) + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, strength), strength));
    }
}