package com.khapilov.customcards.cards.green;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.RossMod;
import com.khapilov.customcards.actions.MakeCardGroupInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 24.10.2018
 */
public class RorschachMask extends CustomCard {
    public static final String ID = "Rorschach Mask";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 1;
    private static final int AMOUNT = 3;

    public RorschachMask() {
        super(ID, NAME, "cards/rorschachMask.png", COST, DESCRIPTION, CardType.SKILL,
                CardColor.GREEN, CardRarity.COMMON, CardTarget.NONE);
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
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new MakeCardGroupInDrawPileAction(magicNumber, RossMod.rorschachCardPool, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RorschachMask();
    }
}
