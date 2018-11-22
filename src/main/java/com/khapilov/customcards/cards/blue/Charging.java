package com.khapilov.customcards.cards.blue;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.RossMod;
import com.khapilov.customcards.helpers.RiseKeyword;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Ross Khapilov
 * @version 1.0 06.11.2018
 */
public class Charging extends CustomCard implements RiseKeyword {
    public static final String ID = "Charging";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 10;
    private static final int AMOUNT = 3;

    public Charging() {
        super(ID, NAME, "cards/charging.png", COST, DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
        if (CardCrawlGame.dungeon != null) rise();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(COST - 1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> temp = new ArrayList<>(RossMod.bluePowerCardPool.group);
        Collections.shuffle(temp);
        ArrayList<AbstractCard> powersCards = new ArrayList<>();


        for (int i = 0; powersCards.size() != AMOUNT; i++) {
            boolean cardIsCorrect = true;
            AbstractCard card = temp.get(i);
            for (AbstractCard c : powersCards) {
                if (c.cost == card.cost) {
                    cardIsCorrect = false;
                    break;
                }
            }
            if (cardIsCorrect) powersCards.add(card.makeCopy());
        }
        powersCards.sort(Comparator.comparingInt(o -> o.cost));
        for (AbstractCard c : powersCards) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
            c.setCostForTurn(0);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Charging();
    }

    @Override
    public void rise() {
        if (cost > 0) upgradeBaseCost(COST - (GameActionManager.turn - 1));
    }
}
