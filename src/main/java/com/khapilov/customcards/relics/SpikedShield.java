package com.khapilov.customcards.relics;

import com.khapilov.customcards.RossMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * @author Ross Khapilov
 * @version 1.0 19.10.2018
 */
public class SpikedShield extends AbstractRelic {
    public static final String ID = "Spiked Shield";
    private static final int STR_AMT = 1;
    private static final int NUM_OF_CARDS = 4;

    public SpikedShield() {
        super(ID, "spikedShield.png", RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + NUM_OF_CARDS + this.DESCRIPTIONS[1] + STR_AMT + this.DESCRIPTIONS[2];
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //because sometimes it doesn't work right
        AbstractCard c = card.makeCopy();
        if (card.upgraded) c.upgrade();

        if (c.keywords.contains(RossMod.keywordStrings.BLOCK.NAMES[0])) {
            counter++;
            if (counter % NUM_OF_CARDS == 0) {
                counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STR_AMT), STR_AMT));
            }
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpikedShield();
    }
}
