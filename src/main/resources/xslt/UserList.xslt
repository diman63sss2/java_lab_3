<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>User Information</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>UUID</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Is Deleted</th>
                        <th>Registration Date</th>
                        <th>Last Modified Date</th>
                        <th>Role</th>
                    </tr>
                    <xsl:for-each select="ArrayList/item">
                        <tr>
                            <td>
                                <a href="{id}">
                                    <xsl:value-of select="id"/>
                                </a>
                            </td>
                            <td><xsl:value-of select="uuid"/></td>
                            <td><xsl:value-of select="username"/></td>
                            <td><xsl:value-of select="firstname"/></td>
                            <td><xsl:value-of select="lastname"/></td>
                            <td><xsl:value-of select="email"/></td>
                            <td><xsl:value-of select="isDeleted"/></td>
                            <td>
                                <xsl:for-each select="registrationDate">
                                    <xsl:value-of select="."/><xsl:text>/</xsl:text>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="lastModifiedDate">
                                    <xsl:value-of select="."/><xsl:text>/</xsl:text>
                                </xsl:for-each>
                            </td>
                            <td><xsl:value-of select="role"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
