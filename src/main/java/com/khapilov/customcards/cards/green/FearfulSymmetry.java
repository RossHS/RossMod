package com.khapilov.customcards.cards.green;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.actions.MakeCardGroupInDrawPileAction;
import com.khapilov.customcards.cards.colorless.RorschachStain;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * @author Ross Khapilov
 * @version 1.0 29.10.2018
 */
public class FearfulSymmetry extends CustomCard {
    public static final String ID = "Fearful Symmetry";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int AMOUNT = 1;

    public FearfulSymmetry() {
        super(ID, NAME, "cards/fearfulSymmetry.png", COST, DESCRIPTION, CardType.SKILL,
                CardColor.GREEN, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = AMOUNT;
        exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card instanceof RorschachStain) {
                for (int i = 0; i < magicNumber; i++) {
                    AbstractCard rs = card.makeCopy();
                    if(card.upgraded) rs.upgrade();
                    cards.add(rs);
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new MakeCardGroupInDrawPileAction(cards, true, true));
    }


    @Override
    public AbstractCard makeCopy() {
        return new FearfulSymmetry();
    }
}
