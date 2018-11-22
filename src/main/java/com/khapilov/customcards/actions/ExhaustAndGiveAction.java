package com.khapilov.customcards.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

/**
 * @author Ross Khapilov
 * @version 1.0 22.11.2018
 */
public class ExhaustAndGiveAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private boolean canPickZero;
    private boolean anyNumber;
    private AbstractPlayer p;

    public ExhaustAndGiveAction(AbstractCreature target, AbstractCreature source, int amount, boolean anyNumber, boolean canPickZero) {
        this.canPickZero = canPickZero;
        this.anyNumber = anyNumber;
        p = (AbstractPlayer) target;
        setValues(target, source, amount);
        duration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
            this.tickDuration();
            CardCrawlGame.dungeon.checkForPactAchievement();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            int numOfCard = AbstractDungeon.handCardSelectScreen.selectedCards.group.size();
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.moveToExhaustPile(c);
            }
            for (int i = 0; i < numOfCard; i++) {
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(getSpecialCard()));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        this.tickDuration();
    }

    private AbstractCard getSpecialCard() {
        AbstractCard.CardRarity rarity = AbstractCard.CardRarity.COMMON;
        int random = MathUtils.random(20);
        if (random == 20) rarity = AbstractCard.CardRarity.RARE;
        else if (random >= 15) rarity = AbstractCard.CardRarity.UNCOMMON;
        AbstractCard temp = AbstractDungeon.getCard(rarity);
        AbstractCard card = temp.makeCopy();
        card.setCostForTurn(0);
        return card;
    }
}
