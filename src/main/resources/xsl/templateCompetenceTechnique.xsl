<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="competenceTechniqueTemplate" match="competenceTechnique">
        <h1>
            COMPÉTENCES TECHNIQUES
        </h1>
        <xsl:for-each select="competenceTechnique/competence">
            <h2>
                <xsl:value-of select="titreCompetence"/>
            </h2>
            <ul>
                <xsl:for-each select="categorie">
                    <li>
                        <xsl:if test="not(titreCategorie='')"><xsl:value-of select="titreCategorie"/>&#160;:&#160;
                        </xsl:if>
                        <xsl:for-each select="libelleCategorie">
                            <xsl:value-of select="."/>
                            <xsl:if test="position() != last()">,&#160;</xsl:if>
                        </xsl:for-each>
                    </li>
                </xsl:for-each>
            </ul>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>