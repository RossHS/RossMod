package com.khapilov.customcards.cards.blue;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.actions.ExhaustAndGiveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 22.11.2018
 */
public class DreamWorld extends CustomCard {
    public static final String ID = "Dream World";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int NUM_OF_CARDS = 3;

    public DreamWorld() {
        super(ID, NAME, "cards/dreamWorld.png", COST, DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        baseMagicNumber = magicNumber = NUM_OF_CARDS;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ExhaustAndGiveAction(p, p, this.magicNumber, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DreamWorld();
    }
}
