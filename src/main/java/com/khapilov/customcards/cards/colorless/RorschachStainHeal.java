package com.khapilov.customcards.cards.colorless;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
public class RorschachStainHeal extends RorschachStain {
    public static final String ID = "Rorschach:Heal";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int HEAL = 2;

    public RorschachStainHeal() {
        super(ID, NAME, "cards/rorschachHeal.png", DESCRIPTION, CardType.SKILL, CardTarget.NONE);
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
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RorschachStainHeal();
    }

    @Override
    protected void addEffect() {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.WHITE.cpy(), true));
    }

    @Override
    protected void setUniqueProperty() {
        baseMagicNumber = magicNumber = HEAL;
    }
}
