package com.khapilov.customcards.cards.red;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

/**
 * @author Ross khapilov
 * @version 1.0 13.10.2018
 */
public class RecklessFlurry extends CustomCard {
    public static final String ID = "Reckless Flurry";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reckless Flurry");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int FRAIL = 1;

    public RecklessFlurry() {
        super(ID, NAME, "cards/recklessFlurry.png", COST, DESCRIPTION, CardType.ATTACK,
                CardColor.RED, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 0;
        exhaust = true;
        isMultiDamage = true;
        baseMagicNumber = FRAIL;
        magicNumber = FRAIL;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RecklessFlurry();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        baseDamage = p.currentBlock;
        calculateCardDamage(m);
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, true), magicNumber));
        rawDescription = DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        baseDamage = AbstractDungeon.player.currentBlock;
        super.applyPowers();
        rawDescription = DESCRIPTION + UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = DESCRIPTION;
        initializeDescription();
    }
}
