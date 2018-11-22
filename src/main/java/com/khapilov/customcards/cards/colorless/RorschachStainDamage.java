package com.khapilov.customcards.cards.colorless;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

/**
 * @author Ross Khapilov
 * @version 1.0 25.10.2018
 */
public class RorschachStainDamage extends RorschachStain {
    public static final String ID = "Rorschach:Damage";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int DAMAGE = 4;

    public RorschachStainDamage() {
        super(ID, NAME, "cards/rorschachDamage.png", DESCRIPTION, CardType.ATTACK, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RorschachStainDamage();
    }

    @Override
    protected void addEffect() {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.RED.cpy(), true));
    }

    @Override
    protected void setUniqueProperty() {
        baseDamage = damage = DAMAGE;
    }
}
