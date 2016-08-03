/*
 * Copyright 2000-2008 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.tfsIntegration.core.tfs.conflicts;

import com.intellij.util.WaitForProgressToShow;
import org.jetbrains.tfsIntegration.ui.ResolveConflictsDialog;

public class DialogConflictsHandler implements ConflictsHandler {
  public void resolveConflicts(final ResolveConflictHelper resolveConflictHelper) {
    if (resolveConflictHelper.getConflicts().isEmpty()) {
      return;
    }

    WaitForProgressToShow.runOrInvokeAndWaitAboveProgress(new Runnable() {
      public void run() {
        ResolveConflictsDialog d = new ResolveConflictsDialog(resolveConflictHelper);
        d.show();
      }
    });
  }
}