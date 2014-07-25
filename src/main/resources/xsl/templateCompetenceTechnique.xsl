<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="competenceTechniqueTemplate" match="competenceTechnique">
        <h2 class="page-header">
            COMPÉTENCES TECHNIQUES
        </h2>
        <xsl:for-each select="competenceTechnique/competence">
            <h3>
                <xsl:value-of select="titreCompetence"/>
            </h3>
            <xsl:for-each select="categorie">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>
                                <xsl:choose>
                                    <xsl:when test="not(titreCategorie='')">
                                        <xsl:value-of select="concat( translate(substring(titreCategorie, 1, 1), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), translate(substring(titreCategorie, 2), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),' ')"/>&#160;:&#160;
                                    </xsl:when>
                                    <xsl:otherwise>
                                        Catégories
                                    </xsl:otherwise>
                                </xsl:choose>
                            </th>
                            <th>Note</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="libelleCategorie">
                            <tr>
                                <td>
                                    <countNo>
                                        <xsl:value-of select="position()"/>
                                    </countNo>
                                </td>
                                <td>
                                    <xsl:value-of select="concat( translate(substring(., 1, 1), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), substring(., 2),' ')"/>
                                    <xsl:if test="position() != last()"></xsl:if>
                                </td>
                                <td>
                                    <ul class="pagination">
                                        <li><a>1</a></li>
                                        <li><a>2</a></li>
                                        <li><a>3</a></li>
                                        <li><a>4</a></li>
                                        <li class="active"><a>5</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </xsl:for-each>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>