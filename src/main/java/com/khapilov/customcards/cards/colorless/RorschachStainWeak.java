package com.khapilov.customcards.cards.colorless;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

/**
 * @author Ross Khapilov
 * @version 1.0 28.10.2018
 */
public class RorschachStainWeak extends RorschachStain {
    public static final String ID = "Rorschach:Weak";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int WEAK = 1;

    public RorschachStainWeak() {
        super(ID, NAME, "cards/rorschachWeak.png", DESCRIPTION, CardType.SKILL, CardTarget.NONE);
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
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RorschachStainWeak();
    }

    @Override
    protected void addEffect() {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.ORANGE.cpy(), true));
    }

    @Override
    protected void setUniqueProperty() {
        baseMagicNumber = magicNumber = WEAK;
    }
}
