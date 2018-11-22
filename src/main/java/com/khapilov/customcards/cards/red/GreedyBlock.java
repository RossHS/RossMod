package com.khapilov.customcards.cards.red;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 04.11.2018
 */
public class GreedyBlock extends CustomCard {
    public static final String ID = "Greedy Block";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    private static final int INCREASE_GAIN_BLOCK = 1;

    public GreedyBlock() {
        super(ID, NAME, "cards/greedyBlock.png", COST, DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 0;
        baseMagicNumber = magicNumber = 0;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(INCREASE_GAIN_BLOCK);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        rawDescription = upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION;
        initializeDescription();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, baseBlock));
    }

    @Override
    public void applyPowers() {
        int counter = 0;
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.cost >= 0)
                counter += card.cost + magicNumber;
        }
        baseBlock = counter;
        super.applyPowers();
        rawDescription = upgraded ? UPGRADE_DESCRIPTION + EXTENDED_DESCRIPTION[0] : DESCRIPTION + EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GreedyBlock();
    }
}
