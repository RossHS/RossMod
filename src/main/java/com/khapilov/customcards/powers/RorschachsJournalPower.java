package com.khapilov.customcards.powers;

import com.khapilov.customcards.RossMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
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
public class RorschachsJournalPower extends AbstractPower {
    public static final String POWER_ID = "Rorschach's Journal";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public RorschachsJournalPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        description = powerStrings.DESCRIPTIONS[0];
        img = ImageMaster.loadImage("powers/rorschachsJournal.png");
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        for (int i = 0; i < amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(RossMod.rorschachCardPool.getRandomCard(true).makeCopy(), 1, true, false));
        }
    }
}
