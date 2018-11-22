package com.khapilov.customcards.cards.blue;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.helpers.RiseKeyword;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 21.11.2018
 */
public class XBlock extends CustomCard implements RiseKeyword {
    public static final String ID = "XBlock";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASE_BLOCK = 5;
    private static final int INCREASE_BLOCk = 1;

    public XBlock() {
        super(ID, NAME, "cards/xBlock.png", COST, DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.COMMON, CardTarget.NONE);
        baseBlock = block = BASE_BLOCK;
        baseMagicNumber = magicNumber = INCREASE_BLOCk;
        if (CardCrawlGame.dungeon != null) rise();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, baseBlock));
    }

    @Override
    public AbstractCard makeCopy() {
        return new XBlock();
    }

    @Override
    public void rise() {
        baseBlock = BASE_BLOCK + magicNumber * (GameActionManager.turn - 1);
    }
}
