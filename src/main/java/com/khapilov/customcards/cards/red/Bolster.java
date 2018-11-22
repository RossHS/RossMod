package com.khapilov.customcards.cards.red;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.RossMod;
import com.khapilov.customcards.actions.DrawPileToHandSpecialAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


/**
 * @author Ross khapilov
 * @version 1.0 14.10.2018
 */
public class Bolster extends CustomCard {
    public static final String ID = "Bolster";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    private static final int AMOUNT = 3;

    public Bolster() {
        super(ID, NAME, "cards/bolster.png", COST, DESCRIPTION, CardType.SKILL,
                CardColor.RED, CardRarity.COMMON, CardTarget.NONE);
        exhaust = true;
        magicNumber = AMOUNT;
        baseMagicNumber = AMOUNT;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawPileToHandSpecialAction(magicNumber, RossMod.keywordStrings.BLOCK.NAMES));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bolster();
    }
}