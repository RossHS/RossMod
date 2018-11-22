package com.khapilov.customcards.cards.blue;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.RossMod;
import com.khapilov.customcards.helpers.RiseKeyword;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 06.11.2018
 */
public class XAttack extends CustomCard implements RiseKeyword {
    public static final String ID = "XAttack";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASE_DAMAGE = 6;
    private static final int INCREASE_DAMAGE = 1;

    public XAttack() {
        super(ID, NAME, "cards/xAttack.png", COST, DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = BASE_DAMAGE;
        baseMagicNumber = magicNumber = INCREASE_DAMAGE;
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
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

//    @Override
//    public void applyPowers() {
//        RossMod.LOGGER.info("Текущий ход (XAttack) - " + GameActionManager.turn);
//        baseDamage = BASE_DAMAGE + magicNumber * (GameActionManager.turn - 1);
//        super.applyPowers();
//    }

    @Override
    public AbstractCard makeCopy() {
        return new XAttack();
    }

    @Override
    public void rise() {
        RossMod.LOGGER.info("Текущий ход (XAttack) - " + GameActionManager.turn);
        baseDamage = BASE_DAMAGE + magicNumber * (GameActionManager.turn - 1);
    }
}
