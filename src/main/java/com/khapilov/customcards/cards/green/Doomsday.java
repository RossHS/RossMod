package com.khapilov.customcards.cards.green;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;

import java.util.Random;

/**
 * @author Ross khapilov
 * @version 1.0 10.10.2018
 */
public class Doomsday extends CustomCard {
    public static final String ID = "Doomsday";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int DAMAGE = 66;
    private static final int POISON_AMT = 6;
    private static final int COST = 4;

    public Doomsday() {
        super(ID, NAME, "cards/doomsday.png", COST, DESCRIPTION, CardType.ATTACK,
                CardColor.GREEN, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = POISON_AMT;
        exhaust = true;
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
        CardCrawlGame.sound.play("POWER_TIME_WARP", 0.15F);
        AbstractDungeon.actionManager.addToTop(new VFXAction(new ScreenOnFireEffect(),0.15F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PoisonPower(abstractPlayer, abstractPlayer, magicNumber), magicNumber));

        for (final AbstractMonster abstractMonster2 : AbstractDungeon.getMonsters().monsters) {
            if (upgraded && abstractMonster != abstractMonster2)
                AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster2, new DamageInfo(abstractPlayer, new Random().nextInt((int) (damage * 0.2)), damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster2, abstractPlayer, new PoisonPower(abstractMonster2, abstractPlayer, magicNumber), magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        return new Doomsday();
    }
}