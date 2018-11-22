package com.khapilov.customcards.relics;

import com.khapilov.customcards.RossMod;
import com.khapilov.customcards.cards.colorless.RorschachStain;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * @author Ross Khapilov
 * @version 1.0 04.11.2018
 */
public class LivelyStain extends AbstractRelic {
    public static final String ID = "Lively Stain";
    private static final int CARD_AMOUNT = 1;
    private static final int NUM_OF_STAINS = 5;

    public LivelyStain() {
        super(ID, "livelyStain.png", RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + NUM_OF_STAINS + this.DESCRIPTIONS[1];
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof RorschachStain) {
            counter++;
            if (counter % NUM_OF_STAINS == 0) {
                counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractCard c = RossMod.rorschachCardPool.getRandomCard(true).makeCopy();
                c.upgrade();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, CARD_AMOUNT, true, true));
            }
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LivelyStain();
    }
}
