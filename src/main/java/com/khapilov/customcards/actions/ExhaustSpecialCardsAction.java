package com.khapilov.customcards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * @author Ross Khapilov
 * @version 1.0 03.11.2018
 */
public class ExhaustSpecialCardsAction extends AbstractGameAction {
    private AbstractCard.CardType type;
    private CardGroup[] cardGroups;

    public ExhaustSpecialCardsAction(AbstractCard.CardType type, CardGroup... cardGroups) {
        this.type = type;
        this.cardGroups = cardGroups;
        setValues(AbstractDungeon.player, AbstractDungeon.player, amount);
        actionType = ActionType.EXHAUST;
        startDuration = Settings.ACTION_DUR_FAST;
        duration = startDuration;
    }

    @Override
    public void update() {
        for (CardGroup group : cardGroups) {
            for (AbstractCard card : group.group) {
                if (card.type.equals(type))
                    AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, group));
            }
        }
        isDone = true;
    }
}
