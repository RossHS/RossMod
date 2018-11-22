package com.khapilov.customcards.actions;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

import java.util.ArrayList;

/**
 * @author Ross Khapilov
 * @version 1.0 27.10.2018
 */
public class MakeCardGroupInDrawPileAction extends AbstractGameAction {
    private ArrayList<AbstractCard> cards;
    private boolean randomSpot;
    private boolean cardOffset;
    private boolean toBottom;

    public MakeCardGroupInDrawPileAction(ArrayList<AbstractCard> cards, boolean randomSpot, boolean cardOffset, boolean toBottom) {
        this.cards = cards;
        this.setValues(this.target, this.source);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = 0.5F;
        this.randomSpot = randomSpot;
        this.cardOffset = cardOffset;
        this.toBottom = toBottom;
    }

    public MakeCardGroupInDrawPileAction(ArrayList<AbstractCard> cards, boolean randomSpot, boolean cardOffset) {
        this(cards, randomSpot, cardOffset, false);
    }

    public MakeCardGroupInDrawPileAction(int numOfCards, CardGroup cardGroup, boolean randomSpot, boolean cardOffset) {
        cards = new ArrayList<>();
        while (cards.size() != numOfCards) {
            cards.add(cardGroup.getRandomCard(true).makeCopy());
        }
        this.setValues(this.target, this.source);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = 0.5F;
        this.randomSpot = randomSpot;
        this.cardOffset = cardOffset;
        this.toBottom = false;
    }


    public void update() {
        if (this.duration == 0.5F) {
            AbstractCard c;
            if (cards.size() < 6) {
                for (AbstractCard card : cards) {
                    UnlockTracker.markCardAsSeen(card.cardID);
                    c = card.makeStatEquivalentCopy();
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, this.randomSpot, this.cardOffset, this.toBottom));
                }
            } else {
                int counter = 0;
                for (AbstractCard card : cards) {
                    UnlockTracker.markCardAsSeen(card.cardID);
                    c = card.makeStatEquivalentCopy();
                    if (counter > 40) {
                        AbstractDungeon.player.drawPile.addToRandomSpot(c);
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, this.randomSpot, this.toBottom));
                    }
                    counter++;
                }
            }
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        this.tickDuration();
    }
}
