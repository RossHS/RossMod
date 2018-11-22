package com.khapilov.customcards.cards.red;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.powers.TankUpPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross khapilov
 * @version 1.0 11.10.2018
 */
public class TankUp extends CustomCard {
    public static final String ID = "Tank Up!";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Tank Up!");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int AMOUNT = 2;

    public TankUp() {
        super(ID, NAME, "cards/tankUp.png", COST, DESCRIPTION, CardType.POWER, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = AMOUNT;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TankUp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TankUpPower(p, magicNumber), magicNumber));
    }
}
