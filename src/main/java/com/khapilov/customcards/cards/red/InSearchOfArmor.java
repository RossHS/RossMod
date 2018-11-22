package com.khapilov.customcards.cards.red;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.actions.InSearchOfArmorAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 17.10.2018
 */
public class InSearchOfArmor extends CustomCard {
    public static final String ID = "In Search of Armor";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("In Search of Armor");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;

    public InSearchOfArmor() {
        super(ID, NAME, "cards/inSearchOfArmor.png", COST, DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new InSearchOfArmorAction(upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new InSearchOfArmor();
    }
}
