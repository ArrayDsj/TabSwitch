package org.intellij.ideaplugins.tabswitch.filefetcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.EditorHistoryManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Creates a list of {@link VirtualFile} by fetching all the files that are open in tabs in current project.
 * <pre>
 * User: must
 * Date: 2012-06-02
 * </pre>
 */
public class FileFetcherOpenTabFiles implements FileFetcher<VirtualFile> {

  @NotNull
  @Override
  public List<VirtualFile> getFiles(Project project) {
    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
    List<VirtualFile> result = getOpenFiles(fileEditorManager, EditorHistoryManager.getInstance(project).getFiles());
    Collections.reverse(result);
    return result;
  }

  @NotNull
  private List<VirtualFile> getOpenFiles(FileEditorManager fileEditorManager, VirtualFile[] files) {
    List<VirtualFile> openFiles = new ArrayList<VirtualFile>();
    for (VirtualFile file : files) {
      if (fileEditorManager.isFileOpen(file) && !openFiles.contains(file)) {
        openFiles.add(file);
      }
    }
    return openFiles;
  }
}
