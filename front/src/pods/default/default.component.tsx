import * as React from "react";
import { useTranslation } from 'react-i18next';


export const DefaultComponent = () => {

    const { t, i18n } = useTranslation();

    const changeLanguage = lng => {
        i18n.changeLanguage(lng);
      };

    return (
        <>
            <button onClick={() => changeLanguage('es')}>es</button>
            <button onClick={() => changeLanguage('en')}>en</button>
            <div>
                <h1>{t('title')}</h1>
            </div>
            <div>{t('description.part2')}</div>
            <div>{t('description.part3')}</div>
            <div>normal string</div>
        </>

    );
}