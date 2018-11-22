package com.khapilov.customcards.actions;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

/**
 * @author Ross Khapilov
 * @version 1.0 08.11.2018
 */
public class ChargingAction extends AbstractGameAction {
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cards;

    public ChargingAction(AbstractPlayer p, ArrayList<AbstractCard> cards) {
        this.p = p;
        this.cards = cards;
        setValues(target, source);
        actionType = ActionType.CARD_MANIPULATION;
        duration = 0.5F;
    }

    @Override
    public void update() {
        if (duration == 0.5F) {
            for (AbstractCard card : cards) {
                UnlockTracker.markCardAsSeen(card.cardID);
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(card, (float) Settings.WIDTH / 2.0F - 100, (float) Settings.HEIGHT / 2.0F - 100));
                p.useCard(card, AbstractDungeon.getRandomMonster(), 0);
            }
            duration -= Gdx.graphics.getDeltaTime();
        }
        this.tickDuration();
    }
}
