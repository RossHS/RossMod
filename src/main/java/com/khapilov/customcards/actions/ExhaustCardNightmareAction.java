package com.khapilov.customcards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Ross Khapilov
 * @version 1.0 27.11.2018
 */
public class ExhaustCardNightmareAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard sourceCard;

    public ExhaustCardNightmareAction(AbstractCreature owner, AbstractCreature source, AbstractCard sourceCard) {
        p = (AbstractPlayer) owner;
        this.sourceCard = sourceCard;
        setValues(owner, source, 1);
        duration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.EXHAUST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> cards = new ArrayList<>(p.hand.group);
            cards.remove(sourceCard);
            if (cards.size() != 0) {
                Collections.shuffle(cards);
                cards.sort((o1, o2) -> o2.cost - o1.cost);
                p.hand.moveToExhaustPile(cards.get(0));
                CardCrawlGame.dungeon.checkForPactAchievement();
            }
        }
        tickDuration();
    }
}
