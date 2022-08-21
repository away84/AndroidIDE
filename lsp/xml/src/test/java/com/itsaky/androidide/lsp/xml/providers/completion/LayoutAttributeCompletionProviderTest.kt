/*
 *  This file is part of AndroidIDE.
 *
 *  AndroidIDE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AndroidIDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with AndroidIDE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.itsaky.androidide.lsp.xml.providers.completion

import com.google.common.truth.Truth.assertThat
import com.itsaky.androidide.lsp.xml.CompletionHelper
import com.itsaky.androidide.lsp.xml.CompletionHelperImpl
import com.itsaky.androidide.lsp.xml.XMLLSPTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/** @author Akash Yadav */
@RunWith(RobolectricTestRunner::class)
class LayoutAttributeCompletionProviderTest : CompletionHelper by CompletionHelperImpl() {

  @Before
  fun setup() {
    XMLLSPTest.initProjectIfNeeded()
  }

  @Test
  fun `attributes from superclasses must be included`() {
    XMLLSPTest.apply {
      openFile("../res/layout/TestAttrsFromSuperclass")
      val (isIncomplete, items) = complete()
      assertThat(isIncomplete).isFalse()
      assertThat(items).isNotEmpty()
      assertThat(items).contains("singleLine") // From TextView
      assertThat(items).contains("scrollbars") // from View
    }
  }

  @Test
  fun `attributes from parent's layout params must be included`() {
    XMLLSPTest.apply {
      openFile("../res/layout/TestAttrsFromLayoutParams")
      val (isIncomplete, items) = complete()
      assertThat(isIncomplete).isFalse()
      assertThat(items).isNotEmpty()
      assertThat(items).contains("lines") // From TextView
      assertThat(items).contains("layout_gravity") // from LinearLayout.LayoutParams
      assertThat(items).contains("layout_weight") // from LinearLayout.LayoutParams
      assertThat(items).contains("layout_width") // from ViewGroup.LayoutParams
    }
  }

  @Test
  fun `attributes from parent's margin layout params must be included`() {
    XMLLSPTest.apply {
      openFile("../res/layout/TestAttrsFromLayoutParams")
      val (isIncomplete, items) = complete()
      assertThat(isIncomplete).isFalse()
      assertThat(items).isNotEmpty()
      assertThat(items).contains("lines") // From TextView
      assertThat(items).contains("layout_gravity") // from LinearLayout.LayoutParams
      assertThat(items).contains("layout_weight") // from LinearLayout.LayoutParams
      assertThat(items).contains("layout_width") // from ViewGroup.LayoutParams
      assertThat(items).contains("layout_margin") // from ViewGroup.MarginLayoutParams
      assertThat(items).contains("layout_marginLeft") // from ViewGroup.MarginLayoutParams
      assertThat(items).contains("layout_marginTop") // from ViewGroup.MarginLayoutParams
      assertThat(items).contains("layout_marginRight") // from ViewGroup.MarginLayoutParams
      assertThat(items).contains("layout_marginBottom") // from ViewGroup.MarginLayoutParams
      assertThat(items).contains("layout_marginStart") // from ViewGroup.MarginLayoutParams
      assertThat(items).contains("layout_marginEnd") // from ViewGroup.MarginLayoutParams
    }
  }
}
