export interface Note {
  id: number | null;
  title: string;
  text: string;
  notebookId: number|null|undefined;
  lastModifiedOn: Date;
}
