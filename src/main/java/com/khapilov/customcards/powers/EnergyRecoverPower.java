package com.khapilov.customcards.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

/**
 * @author Ross Khapilov
 * @version 1.0 created on 14.11.2018
 */
public class EnergyRecoverPower extends AbstractPower {
    public static final String POWER_ID = "Energy Recover";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public EnergyRecoverPower(AbstractPlayer player, int amount) {
        name = NAME;
        ID = POWER_ID;
        owner = player;
        this.amount = amount;
        canGoNegative = true;
        type = PowerType.DEBUFF;
        updateDescription();
        img = ImageMaster.loadImage("powers/energyRecover.png");
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        EnergyPanel.addEnergy(amount);
        amount++;
        if (amount >= 0)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
